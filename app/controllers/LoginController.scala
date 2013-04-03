package controllers

import play.api.mvc.{Action, Controller}
import play.api.data.Form
import play.api.data.Forms._
import models.User

/**
 * User: francois
 * Date: 03/04/13 
 */
object LoginController extends Controller{


  private val loginForm : Form[User] = Form(
    mapping(
      "login" -> nonEmptyText,
      "password" -> nonEmptyText
    )(User.apply)(User.unapply)
  )

  def login = Action{ implicit request =>
    Ok(views.html.login.authenticate(loginForm))
  }


  def doLogin() = Action{ implicit request =>
    this.loginForm.bindFromRequest().fold(
      hasErrors = {
        form =>
          Redirect(routes.LoginController.login()).flashing("error" -> "Login incorrect")
      },
      success = {
        userConnected =>

          Redirect(routes.Application.index())
            .flashing("success" -> ("Bienvenue " +userConnected.login))
            .withSession(request.session + ("userConnected" -> userConnected.login))
      }
    )
  }

}
