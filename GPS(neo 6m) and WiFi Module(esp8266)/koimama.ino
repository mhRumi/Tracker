#include <SoftwareSerial.h>
#include <TinyGPS++.h>
#include <ESP8266WiFi.h>
#include <ESP8266WiFiAP.h>
#include <ESP8266WiFiGeneric.h>
#include <ESP8266WiFiMulti.h>
#include <ESP8266WiFiScan.h>
#include <ESP8266WiFiSTA.h>
#include <ESP8266WiFiType.h>
#include <WiFiClient.h>
#include <WiFiClientSecure.h>
#include <WiFiServer.h>
#include <ESP8266HTTPClient.h>
#include <WiFiUdp.h>


TinyGPSPlus gps;

SoftwareSerial ss(4,5); // for connect GPS

const char* ssid = "DangerZone";
const char* password = "nopassword";

const char* host = "http://messmanagementtool.000webhostapp.com";
String url = "/Tracker/DbConnect.php";

float latitude , longitude;

int year,month,date,hour,minute,second;
String lat_str="44",lng_str="33",gppps="100";

void setup()
{
  Serial.begin(9600);
  ss.begin(9600);
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid,password);

  while(WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi Connected");
}
