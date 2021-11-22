package models.opencollective

import play.api.libs.json.{Json, Reads}

case class OpenCollectiveMember(
  MemberId: Long, // 239380,
  profile: String, // https://opencollective.com/enno-runne,
  name: String, // Enno Runne,
  image: Option[String], // https://www.gravatar.com/avatar/81956674664e179d682db2e431eb4369?default=404,
  twitter: Option[String], // null,
  github: Option[String], // https://github.com/ennru,
  website: Option[String], // https://mywebsite.com
  `type`: String, // USER or ORGANIZATION,
  role: String, // BACKER, ADMIN or HOST,
  isActive: Boolean, // true or false
  totalAmountDonated: Long // amount in cent (USD 100.00 = 10000)
  // createdAt: 2021-11-08 14:41,
  // totalAmountDonated: 0,
  // lastTransactionAt: 2021-11-19 09:16,
  // lastTransactionAmount: 0,
  // company: null,
  // description: Father of three, Husband, JVM professional, Scala Stockholm organizer, Jfokus co-organizer, Swede by choice, Akka Serverless manager @ Lightbend,
  // email: null,
) {
  def id = MemberId
  def slug = profile.substring("https://opencollective.com/".size)
  def link = website.getOrElse(twitter.getOrElse(github.getOrElse(profile)))
}

object OpenCollectiveMember {
  implicit val jsonReads: Reads[OpenCollectiveMember] = Json.reads[OpenCollectiveMember]
}