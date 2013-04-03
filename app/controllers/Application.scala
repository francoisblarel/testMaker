package controllers

import play.api.mvc._

object Application extends Controller with SecurityTrait{

  def index = isAuthenticated{ username => implicit request =>
    Ok(views.html.index("Your new application is ready."))
  }

}