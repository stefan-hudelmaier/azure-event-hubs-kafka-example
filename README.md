# azure-event-hubs-kafka-example

This is a minimal example using Spring Boot and Spring Kafka to connect to Azure Event Hubs via the Kafka protocol.

You can use the following guide to create a Kafka-enabled Event Hubs on Azure:

https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-create-kafka-enabled

Create a topic aka Event Hub called `test-topic`

You now have to configure the following two Spring properties, e.g. in `application.yml`.


```$yml
event-hubs-connection-string: "Endpoint=sb://sth-event-hubs.servicebus.windows.net/;SharedAccessKeyName=sth-policy;SharedAccessKey=xxxxxx"
event-hubs-fqdn: "sth-event-hubs.servicebus.windows.net"
```

You can send a test message using:

```$bash
curl http://127.0.0.1:8080/send-message
```

Since the application listens for messages on `test-topic` you
should see the messages in the log. Note that it can take a couple of seconds for the application to subscribe.
