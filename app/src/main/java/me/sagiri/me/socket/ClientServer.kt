package me.sagiri.me.socket

import android.app.Service
import android.content.Intent
import android.os.IBinder
import me.sagiri.char.client.Client
import me.sagiri.char.client.ClientConnectEvent
import me.sagiri.char.client.ClientMsgEvent
import me.sagiri.char.server.Server

class ClientServer : Service() {
    private lateinit var client : Client
    private  lateinit var host : String
    private lateinit var port : String
    private lateinit var name : String


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            val host = intent.getStringExtra("host")
            val port = intent.getStringExtra("port")
            val name = intent.getStringExtra("name")

            println("host $host port $port name $name")
            if(host.isNotEmpty() && port.isNotEmpty() && name.isNotEmpty()) {
                this.host = host
                this.port = port
                this.name = name
                connect()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        /**
        var i = 0
        Thread(Runnable {
            while (true) {
                println("${i}只羊")
                if(i >= 100) {
                    println("睡着了...")
                    break
                }
                i ++
                Thread.sleep(1000)
            }
        }).start()
        **/
    }

    private  fun connect() {
        Thread(Runnable {
            Client("10.0.0.101", 8888, "sagiri").apply {
                setConnectEvent(object : ClientConnectEvent {
                    override fun onConnect() {
                        super.onConnect()
                        println("连接成功")
                    }

                    override fun onFailed() {
                        super.onFailed()
                        println("连接失败")
                    }

                    override fun onClose() {
                        super.onClose()
                        println("断开连接")
                    }
                })

                setMsgEvent(object : ClientMsgEvent {
                    override fun onMsg(msg: String) {
                        println(msg)
                    }
                })

                connect()
            }
        }).start()
    }
}
