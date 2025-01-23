const amqp = require('amqplib');
const queue = 'hello';
const CONSUMER_COUNT = 5;


async function createConsumer(id) {
    const connection = await amqp.connect("amqps://qjkgexno:CGzpDhQdexE4adOGaQw88RZ3PM4tsi8M@sparrow.rmq.cloudamqp.com/qjkgexno");
    const channel = await connection.createChannel();
    await channel.assertQueue(queue, { durable: true });
    console.log(`Consumer ${id} Started`);
    channel.consume(queue, (msg) => {
        if (msg !== null) {
            console.log(`Consumer ${id} Processing ${msg.content.toString()}`);
            setTimeout(() => {
                channel.ack(msg);
                console.log(`Consumer ${id} is Done`);
            }, 1000);
        }
    })
}

async function startConsumer() {
    for (let i = 1; i <= CONSUMER_COUNT; i++) {
        createConsumer(i).catch(console.error);
    }

}
startConsumer();