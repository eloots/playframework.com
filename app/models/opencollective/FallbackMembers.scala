package models.opencollective

/**
 * Pull from OpenCollective on 11/22/2021 so that we have something to fall back to when OpenCollective is either down, or if
 * in development we've exceeded the OpenCollective rate limit quotas.
 *
 * This is also used as the initial data when Play first starts up, and then is soon replaced by the actual data
 * when we've finished pulling it from OpenCollective.
 */
object FallbackMembers {

  // format: off
  val members = Seq(OpenCollectiveMember(2, "https://opencollective.com/john-doe", "John Doe", Some("https://www.gravatar.com/avatar/foo.jpg"), Some("https://twitter.com/john_d"), Some("https://github.com/johhny"), Some("https://john.doe.com"), "BACKER", "USER", true, 122))
//    OpenCollectiveMember(
//    committers = List(
//      OpenCollectiveMember(id = 105833, login = "jroper", url = "https://api.github.com/users/jroper", avatar_url = "https://avatars.OpenCollectiveMembercontent.com/u/105833?v=3", html_url = "https://github.com/jroper", name = Some("James Roper")),
//    ),
//  )
  // format: on

  /**
   * Code to generate the code above from a current list of members
   */
//  def dumpMembers(contributors: OpenCollectiveMember) = {
//    def option(s: Option[String]) = s.fold("None")("Some(\"" + _ + "\")")
//    def formatUser(u: OpenCollectiveMember): String = {
//      import u._
//      // format: off
//      s"""OpenCollectiveMember(id = $id, login = "$login", url = "$url", avatar_url = "$avatar_url", html_url = "$html_url", name = ${option(name)})"""
//      // format: on
//    }
//
//    def formatUsers(users: Seq[OpenCollectiveMember]) = users.map(user => "        " + formatUser(user)).mkString(",\n")
//
//    s"""
//       |    Contributors(
//       |      committers = List(
//       |${formatUsers(contributors.committers)}
//       |      ),
//       |      playOrganisation = List(
//       |${formatUsers(contributors.playOrganisation)}
//       |      ),
//       |      contributors = List(
//       |${formatUsers(contributors.contributors)}
//       |      )
//       |    )""".stripMargin
//  }

}
