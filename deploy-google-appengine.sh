if [ -f "~/twilio.properties" ]; then
  cp ~/twilio.properties ./src/main/resources
fi
mvn appengine:deploy
if [ -f "./src/main/resources/twilio.properties" ]; then
  git checkout ./src/main/resources/twilio.properties
fi
