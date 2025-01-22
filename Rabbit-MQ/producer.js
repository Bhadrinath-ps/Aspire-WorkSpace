const amqp = require('amqplib');

async function sendMessage() {
    const connection = await amqp.connect("amqps://qjkgexno:CGzpDhQdexE4adOGaQw88RZ3PM4tsi8M@sparrow.rmq.cloudamqp.com/qjkgexno");
    const channel = await connection.createChannel();
    const queue = "aspire";
    await channel.assertQueue(queue, { durable: false });
    channel.sendToQueue(queue, Buffer.from("Hello Aspirians"));

    setTimeout(() => {
        channel.close();
        connection.close();
    }, 500)
}

sendMessage();