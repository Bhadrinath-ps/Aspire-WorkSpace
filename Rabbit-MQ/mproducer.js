const amqp = require('amqplib');
const queue = 'hello';
 
async function sendMessage() {
    const connection = await amqp.connect("amqps://qjkgexno:CGzpDhQdexE4adOGaQw88RZ3PM4tsi8M@sparrow.rmq.cloudamqp.com/qjkgexno");
    const channel = await connection.createChannel();
    await channel.assertQueue(queue, { durable: true });
 
    for (let i = 1; i <= 10; i++) {
        const message = `Message ${i}`;
        channel.sendToQueue(queue, Buffer.from(message), { persistent: true });
        console.log(`Sent: ${message}`);
    }
 
    await channel.close();
    await connection.close();
}
 
sendMessage().catch(console.error);