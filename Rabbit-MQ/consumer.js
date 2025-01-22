const amqp = require('amqplib');

async function receiveMessage() {
    const connection = await amqp.connect("amqps://qjkgexno:CGzpDhQdexE4adOGaQw88RZ3PM4tsi8M@sparrow.rmq.cloudamqp.com/qjkgexno");
    const channel = await connection.createChannel();
    const queue = "aspire";
    await channel.assertQueue(queue, { durable: false });
    channel.consume(queue, (msg) => {
        console.log("%s", msg.content.toString());
    }, { noAck: true })
}

receiveMessage();