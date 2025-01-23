const amqp = require('amqplib');
const queue = "hello";
const PREFETCH_COUNT = 5; // Message Fetched per Consumer  

async function startConsumer() {
    const connection = await amqp.connect('amqps://qjkgexno:CGzpDhQdexE4adOGaQw88RZ3PM4tsi8M@sparrow.rmq.cloudamqp.com/qjkgexno');
    const channel = await connection.createChannel();
    await channel.assertQueue(queue, { durable: true });
    channel.prefetch(PREFETCH_COUNT)
    console.log(`Consumer Started`);
    channel.consume(queue, (msg) => {
        if (msg !== null) {
            console.log(`Consumer ${id} Processing ${msg.content.toString()}`);
            setTimeout(() => {
                channel.ack(msg);
                console.log(`Message Processed`);
            }), 1000;
        }
    })
}

startConsumer().catch(console.error);