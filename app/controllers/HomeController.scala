package controllers

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import javax.inject._

import play.api.mvc.{Action, Controller, Result}

import scala.sys.process._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject() extends Controller {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index = Action { implicit request =>
    request.body.asText.fold[Result](BadRequest)(v => {
      println(v)
      Files.write(Paths.get(".","graph.dot"), v.getBytes)
//      val dotFile = new PrintWriter("graph.dot")
//      dotFile.println(v)
//      dotFile.close
      "dot -Tsvg graph.dot -o graph.svg" !;
      Ok.sendPath(Paths.get(".", "graph.svg"))
    })
  }
}
