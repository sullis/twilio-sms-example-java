cp ~/twilio.properties ./src/main/resources
mvn appengine:deploy
git checkout ./src/main/resources/twilio.properties
