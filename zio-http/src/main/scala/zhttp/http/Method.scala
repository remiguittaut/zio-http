package zhttp.http

import io.netty.handler.codec.http.{HttpMethod => JHttpMethod}

sealed trait Method { self =>
  lazy val asJHttpMethod: JHttpMethod = Method.asJHttpMethod(self)
}

object Method {
  object OPTIONS                  extends Method
  object GET                      extends Method
  object HEAD                     extends Method
  object POST                     extends Method
  object PUT                      extends Method
  object PATCH                    extends Method
  object DELETE                   extends Method
  object TRACE                    extends Method
  object CONNECT                  extends Method
  case class CUSTOM(name: String) extends Method

  def fromJHttpMethod(method: JHttpMethod): Method =
    method match {
      case JHttpMethod.OPTIONS => OPTIONS
      case JHttpMethod.GET     => GET
      case JHttpMethod.HEAD    => HEAD
      case JHttpMethod.POST    => POST
      case JHttpMethod.PUT     => PUT
      case JHttpMethod.PATCH   => PATCH
      case JHttpMethod.DELETE  => DELETE
      case JHttpMethod.TRACE   => TRACE
      case JHttpMethod.CONNECT => CONNECT
      case method              => CUSTOM(method.name())
    }

  def asJHttpMethod(self: Method): JHttpMethod = self match {
    case OPTIONS      => JHttpMethod.OPTIONS
    case GET          => JHttpMethod.GET
    case HEAD         => JHttpMethod.HEAD
    case POST         => JHttpMethod.POST
    case PUT          => JHttpMethod.PUT
    case PATCH        => JHttpMethod.PATCH
    case DELETE       => JHttpMethod.DELETE
    case TRACE        => JHttpMethod.TRACE
    case CONNECT      => JHttpMethod.CONNECT
    case CUSTOM(name) => new JHttpMethod(name)
  }
}
