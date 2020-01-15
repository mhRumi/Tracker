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

const char* ssid = "*******"; //Wifi SSID Name
const char* password = "*******"; //Password of the Wifi

const char* host = "**********"; //Servername or localhost address name
String url = "**********"; //Server hit url

float latitude , longitude;

int year,month,date,hour,minute,second;
String lat_str="44",lng_str="33",gppps="100",id="*****";

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

void loop()
{
  {
    while (ss.available()>0)
    if(gps.encode(ss.read()))
    {
      if(gps.location.isValid())
      {
        latitude = gps.location.lat();
        lat_str = String(latitude , 6);
        longitude = gps.location.lng();
        lng_str = String(longitude , 6);
        Serial.println(lat_str + " " + lng_str);
        String loc = "Id="+id+"&GpsId=" + gppps + "&Latitude=" + lat_str + "&Longitude=" + lng_str;
        send(loc);
      }
    }
  }
}

void send(String loc)
{
    Serial.print(loc);
  {
    if(WiFi.status() == WL_CONNECTED){
      HTTPClient http;
      WiFiClient client;
      const int httpport = 80;
      if(!client.connect(host, httpport)) //Comments below dotted line when using localhost as server
      { // ...
        Serial.println("Failed"); // ...
        return ; // ...
      } // ...
      Serial.println("Requesting");
      String address = host + url;

      http.begin(address);
      http.addHeader("Content-Type", "application/x-www-form-urlencoded"); 
      http.POST(loc);
      http.end();
      
      delay(3000);
    }
  }
}
