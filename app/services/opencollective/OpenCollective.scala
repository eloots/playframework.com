package services.opencollective

import models.opencollective.OpenCollectiveMember
import play.api.http.HeaderNames
import play.api.libs.json.Reads
import play.api.libs.ws.{WSClient, WSResponse}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

case class OpenCollectiveConfig(
  openCollectiveMembersApiUrl: String,
)

/**
 * Interface to making remote calls on OpenCollective
 */
trait OpenCollective {

  /**
   * Get the members
   */
  def fetchMembers(): Future[Seq[OpenCollectiveMember]]

}

class DefaultOpenCollective @Inject()(ws: WSClient, config: OpenCollectiveConfig)(implicit ec: ExecutionContext)
  extends OpenCollective {

  private def load[T: Reads] = { // (path: String)
    //val url = if (path.matches("https?://.*")) path else config.apiUrl + path
    val url = config.openCollectiveMembersApiUrl
    ws.url(url).get().map { response =>
      checkSuccess(response).json.as[Seq[T]]
    }
  }

  private def responseFailure(response: WSResponse): Exception = response.status match {
    case 403 =>
      new RuntimeException("Request forbidden, please check OpenCollective API: " + response.body)
    case error => new RuntimeException("Request failed with " + response.status + " " + response.statusText)
  }

  private def checkSuccess(response: WSResponse): WSResponse = response.status match {
    case ok if ok < 300 => response
    case _ => throw responseFailure(response)
  }

  /**
   * Get the members
   */
  override def fetchMembers(): Future[Seq[OpenCollectiveMember]] =
    load[OpenCollectiveMember]
}
