import RPi.GPIO as gpio
import time
import paho.mqtt.client as mqtt

yled_pin = 20
rled_pin = 16
gled_pin = 21

gpio.setmode(gpio.BCM)
gpio.setup(yled_pin, gpio.OUT)
gpio.setup(rled_pin, gpio.OUT)
gpio.setup(gled_pin, gpio.OUT)
LED = ""

global LED

def on_connect(client, userdata, flags, rc):
    print("connected with result code " + str(rc))
    client.subscribe("control/led")

def on_message(client, userdata, msg):
    print("Topic: " + msg.topic + " Message: " + msg.payload)
<<<<<<< HEAD
    global LED
    LED = msg.payload
                     
client = mqtt.Client()                     
=======
    LED = msg.paylode

client = mqtt.Client;
>>>>>>> e201566aaa0ebd2069c37dfe241fa3c690f382b3
client.on_connect = on_connect
client.on_message = on_message

client.connect("127.0.0.1", 1883, 60)
client.loop_start()
try:
    while True:
        if LED == "Green" :
	    gpio.output(gled_pin, True)
	    gpio.output(rled_pin, False)
	    gpio.output(yled_pin, False)
        elif LED == "Yellow" :
	    gpio.output(gled_pin, False)
	    gpio.output(rled_pin, False)
	    gpio.output(yled_pin, True)
        elif LED == "Red" :
	    gpio.output(gled_pin, False)
	    gpio.output(rled_pin, True)
	    gpio.output(yled_pin, False)
        elif LED == "All" :
	    gpio.output(gled_pin, True)
	    gpio.output(rled_pin, True)
	    gpio.output(yled_pin, True)

except KeyboardInterrupt:
    print("Finished!")
    client.unsubscribe(["control/led"])
    gpio.cleanup()
    client.loop_stop()
    client.disconnect()
