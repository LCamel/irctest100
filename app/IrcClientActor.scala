
import akka.actor.Actor
import com.ircclouds.irc.api.IRCApiImpl
import com.ircclouds.irc.api.IServerParameters
import com.ircclouds.irc.api.Callback
import com.ircclouds.irc.api.state.IIRCState
import com.ircclouds.irc.api.domain.IRCChannel

class IrcClientActor extends Actor {
  val ircApi = new IRCApiImpl(true)
  
  def receive: Actor.Receive = {
    case "connect" => {
      val params = new IServerParameters() {
        import scala.collection.JavaConverters._
        import com.ircclouds.irc.api.domain.IRCServer
        def getAlternativeNicknames(): java.util.List[String] = Seq("lcamel1", "lcamel2").asJava
        def getIdent(): String = "localhost"
        def getNickname(): String = "lcamel0"
        def getRealname(): String = "lcamelbot"
        def getServer(): IRCServer = new IRCServer("chat.freenode.net")         
      };
      val aCallback = new Callback[IIRCState]() {
        def onFailure(x$1: Exception): Unit = { println("connect onFailure!") }
        def onSuccess(x$1: IIRCState): Unit = { println("connect onSuccess!") }
      }
      ircApi.connect(params, aCallback)
    }
    case "join" => {
      val aCallback = new Callback[IRCChannel] {
        def onFailure(x$1: Exception): Unit = { println("join onFailure! " + x$1) }
        def onSuccess(x$1: IRCChannel): Unit = { println("join onSuccess! " + x$1) }
        
      }
      ircApi.joinChannel("#lcameltest", "", aCallback)
    }
  }
}
/*
import akka.actor._
val system = ActorSystem("mySystem")
val myActor = system.actorOf(Props[IrcClientActor], "myactor2")
myActor ! "connect"
*/
