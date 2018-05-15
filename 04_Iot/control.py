import paho.mqtt.client as mqtt
import time
tem = 0
hum = 0
distance = 0.0
def on_connect(client, userdata, flags, rc):
    print("connected with result code " + str(rc))
    client.subscribe("environment/distance")
    client.subscribe("environment/temperature")
    client.subscribe("environment/humidity")

def on_message(client, userdata, msg):
    global tem
    global hum
    global distance
    if msg.topic == "environment/distance" :
	   distance = float(msg.payload)
    if msg.topic == "environment/temperature" :
	   tem = float(msg.payload)
    if msg.topic == "environment/humidity" :
<<<<<<< HEAD
	   hum = float(msg.payload)
	   
def on_publish(client, userdata, mid):
    print("message published")
    
=======
	   hum = msg.payload

def on_publish(client, userdata, mid):
	print("message published")

>>>>>>> e201566aaa0ebd2069c37dfe241fa3c690f382b3
client = mqtt.Client()
client.on_connect = on_connect
client.on_message = on_message
client.on_publish = on_publish
client.connect("127.0.0.1", 1883, 60)
client.loop_start()

try:
    while True :
<<<<<<< HEAD
        if tem > 35 and hum > 50:
            print("Publish data : LED - All, " + str(tem) + ", "+ str(hum))
            client.publish("control/led", "All")
=======
        if tem > 20 and hum > 40 :
            print("Publish data : LED - All")
            client.publish("control/led",All)
>>>>>>> e201566aaa0ebd2069c37dfe241fa3c690f382b3
        elif distance >= 50 :
            print("Publish data : LED - Green, " + str(distance) )
            client.publish("control/led", "Green")
        elif distance > 20 :
            print("Publush data : LED - yellow, " + str(distance) )
            client.publish("control/led", "Yellow")
        else :
            print("Publish data : LED - Red, " + str(distance) )
            client.publish("control/led", "Red")
        time.sleep(2)

except KeyboardInterrupt:
    client.loop_stop()
    client.unsubscribe(["environment/distance", "environment/temperature", "environment/humidity"])
    client.disconnect()

