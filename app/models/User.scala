package models

/**
 * User: fblarel
 * Date: 29/03/13 
 */
case class User(login : String, password : String)

object User {

  val mapLogins = List(User("",""))

  def exists(userName : String) : Boolean = true

}