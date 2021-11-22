package services.opencollective

import org.slf4j.LoggerFactory
import play.api.{Configuration, Environment}
import play.api.inject.Module

class OpenCollectiveModule extends Module {
  private val log = LoggerFactory.getLogger(classOf[OpenCollectiveModule])

  def bindings(environment: Environment, configuration: Configuration) = {
    import scala.jdk.CollectionConverters._
    val openCollectiveMembersApiUrl   = configuration.underlying.getString("opencollective.membersUrl")

        Seq(
 bind[OpenCollectiveConfig].to(OpenCollectiveConfig(openCollectiveMembersApiUrl)),
          bind[OpenCollective].to[DefaultOpenCollective],
          bind[MembersSummariser]
            .qualifiedWith("openCollectiveMembersSummariser")
            .to[DefaultMembersSummariser],
          bind[MembersSummariser].to[CachingMembersSummariser],
        )

  }
}
