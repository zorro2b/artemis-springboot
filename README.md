# ActiveMQ Artemis SpringBoot Example

A single SpringBoot application that can be configured as a JMS topic publisher or subscriber.

Use:

    docker-compose up

To start an Artemis server with one publisher and two subscribers.

The publisher will log when it sends messages and the subscribers will og when a message is received,
for example:

    pub_1   | 2021-01-15 15:32:35.525  INFO 1 --- [   scheduling-1] com.example.amqdemo.ScheduleConfig       : Sending a message.
    sub1_1  | 2021-01-15 15:32:35.535  INFO 1 --- [enerContainer-2] com.example.amqdemo.Topic1Receiver       : Message read from topic 1 : CustomMessage{text='Topic 1 message', sequence=2, secret=false, sent=2021-01-15T15:32:35.526117} transfer time: 8882µs
    sub2_1  | 2021-01-15 15:32:35.542  INFO 1 --- [enerContainer-2] com.example.amqdemo.Topic2Receiver       : Message read from topic 2 : CustomMessage{text='Topic 2 message', sequence=1, secret=false, sent=2021-01-15T15:32:35.534279} transfer time: 8498µs

Messages are sent from ScheduleConfig.java.

The JmsListenerContainerFactory is set up as a bean in AmqdemoAppication.java.

When using durable queues, you can shutdown the subscribers and then start them
up again to see that they still receive messages that were sent while they were down.
