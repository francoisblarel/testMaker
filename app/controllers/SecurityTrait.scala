package controllers

import play.api.mvc._
import play.api.mvc.AnyContent
import play.api.mvc.Result
import play.api.mvc.Security._

/**
 * User: francois
 * Date: 03/04/13 
 */
trait SecurityTrait {


  /*
  def isAuthenticated(f: => String => Request[AnyContent] => Result) = {
    Authenticated { user =>
      Action(request => f(user)(request))
    }
  }
*/

  //in a Security trait
  def username(request: RequestHeader) = request.session.get("userConnected")

  def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.LoginController.login)

  def isAuthenticated(f: => String => Request[AnyContent] => Result) = {
    Authenticated(username, onUnauthorized) { user =>
      Action(request => f(user)(request))
    }
  }


}
