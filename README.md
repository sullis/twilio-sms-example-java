# twilio-sms-example-java

This application uses the [Twilio Java SDK](https://github.com/twilio/twilio-java)

# Run tests

```
$ mvn clean test
```

# Deploy to Google App Engine

```
$ ./deploy-google-appengine.sh
```

# Build a Docker image

```
$ mvn clean package jib:build
```

