import RPi.GPIO as gpio
import paho.mqtt.client as mqtt
import spidev
import time

trig_pin = 13
echo_pin = 19

gpio.setmode(gpio.BCM)
gpio.setup(trig_pin, gpio.OUT)
gpio.setup(echo_pin, gpio.IN)

def on_connect(client, userdata, flags, rc):
    print("Connected with result code " + str(rc))

def on_publish(client, userdata, mid):
    print("message published")

mqttc = mqtt.Client()
mqttc.on_connect = on_connect
mqttc.on_publish = on_publish
mqttc.connect("127.0.0.1")
mqttc.loop_start()

try:
        while True:
                gpio.output(trig_pin, False)
		time.sleep(1)

		gpio.output(trig_pin, True)
		time.sleep(0.00001)
		gpio.output(trig_pin, False)
		
		while gpio.input(echo_pin) == 0:
		    pulse_start = time.time()

		while gpio.input(echo_pin) == 1:
		    pulse_end = time.time()    
		
		pulse_duration = pulse_end - pulse_start
		distance = pulse_duration * 17000
		distance = round(distance, 2)

                mqttc.publish("environment/distance", distance)
		print(" publish Distance 	: %f cm" %distance)
                time.sleep(1)
                
except KeyboardInterrupt:
	print("finished")
	gpio.cleanup()
	mqttc.loop_stop()
        mqttc.disconnect()
