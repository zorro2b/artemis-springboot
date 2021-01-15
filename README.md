# ActiveMQ Artemis SpringBoot Example

A single SpringBoot application that can be configured as a JMS topic publisher or subscriber.

Use:

    docker-compose up

To start an Artemis server with one publisher and two subscribers.

Note that the subscriptions can be set to durable with "DURABLE=true",
however this currently results in the messages not being received by the subscribers.
