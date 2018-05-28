#include<iostream>
#include<opencv2/opencv.hpp>
std::vector<std::string> GetTrainFiles(std::string filename) {
	std::vector<std::string> ret;
	std::fstream fin(filename, std::ios::in);
	if (fin.is_open() == false) {
		std::cout << "Incorrect file path" << std::endl;
		exit(1);
	}
	while (fin.eof() == false) {
		std::string line;
		std::getline(fin, line);
		if (line.length() == 0) {
			break;
		}
		ret.push_back(line);
	}
	fin.close();
	return ret;
}
std::vector<float> IOU(std::pair<float, float> val, std::vector<std::pair<float, float>> centroids) {
	std::vector<float> similarities;
	for (size_t i = 0; i < centroids.size(); i++) {
		float s = 0.0F;
		float& w = val.first;
		float& h = val.second;
		float& cw = centroids[i].first;
		float& ch = centroids[i].second;
		if (cw > w && ch > h) {
			s = (w*h) / (cw*ch);
		} else if (cw > w && ch <= h) {
			s = w*ch / (w*h + (cw - w)*ch);
		} else if (cw <= w && ch >= h) {
			s = cw*h / (w*h + cw*(ch - h));
		} else {
			s = (cw*ch) / (w*h);
		}
		similarities.push_back(1.0F-s);
	}
	return similarities;
}
std::vector<std::pair<float, float>> KMeans(std::vector<std::pair<float, float>> X, std::vector<std::pair<float, float>> centroids){
	std::vector<std::pair<float, float>> ret;
	size_t N = X.size();
	size_t K = centroids.size();
	std::vector<std::vector<float>> D;
	std::vector<int> prev_assignments;
	while (true) {
		for (int i = 0; i < N; i++) {
			std::vector<float> d = IOU(X[i], centroids);
			D.push_back(d);
		}
		std::vector<int> assignments;
		for (int i = 0; i < N; i++) {
			int a = static_cast<int>(std::min_element(D[i].begin(), D[i].end()) - D[i].begin());
			assignments.push_back(a);
		}
		if (assignments == prev_assignments) {
			ret = centroids;
			break;
		}
		std::vector<std::pair<float, float>> centroid_sums;
		centroid_sums.assign(K, std::pair<float,float>(0.0F,0.0F));
		for (int i = 0; i < N; i++) {
			centroid_sums[assignments[i]].first += X[i].first;
			centroid_sums[assignments[i]].second += X[i].second;
		}
		for (int i = 0; i < K; i++) {
			size_t cnt = std::max(std::count(assignments.begin(), assignments.end(), i), 1LL);
			centroids[i].first = centroid_sums[i].first / cnt;
			centroids[i].second = centroid_sums[i].second / cnt;
		}
		prev_assignments = assignments;
	}
	
	std::sort(ret.begin(), ret.end());
	return ret;
}

int main() {
	const int NUM_CLUSTERS = 7;
	const int WH = 416;
	std::string train_file = "train.txt";
	std::vector<std::string> files = GetTrainFiles(train_file);
	std::vector<std::pair<float, float>> annotation_dims;
	for (auto&file : files) {
		file=file.substr(0, file.find_last_of('.')) + ".txt";
		std::fstream fin(file, std::ios::in);
		if (fin.is_open() == false) {
			std::cerr << "No files" << std::endl;
			exit(1);
		}
		while (fin.eof()==false) {
			std::string line;
			std::getline(fin, line);
			if (line.length() == 0)break;
			std::istringstream iss;
			iss.str(line);
			float c, x, y, w, h;
			iss >> c >> x >> y >> w >> h;
			annotation_dims.push_back(std::make_pair(w, h));
		}
		fin.close();
	}
	std::sort(annotation_dims.begin(), annotation_dims.end(), [](std::pair<float, float> a, std::pair<float, float> b)->bool {
		return a.first*a.second < b.first*b.second;
	});
	std::vector<std::pair<float, float>> centroids;

	for (int i = 0; i < NUM_CLUSTERS; i++) {
		centroids.push_back(annotation_dims[annotation_dims.size()/(NUM_CLUSTERS+2)*(i+1)]);
	}
	centroids = KMeans(annotation_dims, centroids);
	
	for (auto&e : centroids) {
		e.first *= WH / 32;
		e.second *= WH / 32;
	}
	std::cout.precision(3);
	for (int i = 0; i < centroids.size()-1; i++) {
		std::cout << centroids[i].first << "," << centroids[i].second << ", ";
	}
	std::cout << centroids.back().first << "," << centroids.back().second << std::endl;

	cv::Mat img = cv::Mat::zeros(WH, WH, CV_8UC3) + cv::Scalar(255, 255, 255);

	for (int i = 0; i < centroids.size(); i++) {
		cv::rectangle(img, cv::Rect(10 + i * 5, 10 + i * 5, centroids[i].first*32, centroids[i].second*32), cv::Scalar(rand() % 255, rand() % 255, rand() % 255), 2);
	}

	cv::imshow("img", img);
	cv::waitKey();
	cv::destroyAllWindows();
	return 0;
}