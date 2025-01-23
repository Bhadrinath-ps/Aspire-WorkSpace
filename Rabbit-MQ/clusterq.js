const cluster=require('cluster');
const os=require('os');
const amqplib=require('amqlib');
 
const queue="hello";
if(cluster.isMaster){
    const noWorkers=os.cpus().length;
    console.log(`Master is working with ${noWorkers}`);
    for(let i=0;i<noWorkers;i++)
        cluster.fork()
 
    cluster.on('exit',(worker)=>{
        cluster.fork()
    })
}
else{
    async function startConsumer(){
         const connection=await amqp.connect("amqps://qjkgexno:CGzpDhQdexE4adOGaQw88RZ3PM4tsi8M@sparrow.rmq.cloudamqp.com/qjkgexno");
            const channel=await connection.createChannel();
            await channel.assertQueue(queue,{durable:true});
            console.log(`consumer ${id} started`);
            channel.consume(queue,(msg)=>{
                if(msg!==null){
                    console.log(`consumer ${id} Processing ${msg.content.toString()}`)
                    setTimeout(()=>{
                        channel.ack(msg);
                        console.log(`Consumer ${id} is Done`)
                    },1000);
                }
            })
    }
    startConsumer().catch(console.error)
}