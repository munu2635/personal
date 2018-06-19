from sklearn import tree

## Collect Training Data
# 1 = "smooth", 0 = "bumpy"
features = [[140, 1], [130, 1], [150, 0], [170, 0]]
# 0 = "apple", 1 = "orange"
labels = [0, 0, 1, 1]


## Train Classifier - Decision Tree
# in 사이킷 트레이닝 알고리즘 = 핏(fit) = 데이터에서 패턴을 발견하다.

# global clf
clf = tree.DecisionTreeClassifier()
clf = clf.fit(features, labels)
print(clf.predict([[150, 0]]))


# need more python data.. 
