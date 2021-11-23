package services.opencollective

import play.api.{Configuration, Environment}
import play.api.inject.Module

class OpenCollectiveModule extends Module {

  def bindings(environment: Environment, configuration: Configuration) = {
    val openCollectiveMembersApiUrl = configuration.underlying.getString("opencollective.membersUrl")

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
