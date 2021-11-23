package models.opencollective

import play.api.libs.json.{Json, Reads}

case class OpenCollectiveMember(
  MemberId: Long, // 123456,
  profile: String, // https://opencollective.com/john-doe,
  name: String, // John Doe,
  image: Option[String], // https://www.gravatar.com/avatar/81956674664e179d682db2e431eb4369?default=404,
  twitter: Option[String], // https://twitter.com/john-doe,
  github: Option[String], // https://github.com/john-doe,
  website: Option[String], // https://john-doe.com
  `type`: String, // USER or ORGANIZATION,
  role: String, // BACKER, ADMIN or HOST,
  isActive: Boolean, // true or false
  totalAmountDonated: Long // amount in cent (USD 100.00 = 10000)
  // createdAt: 2021-11-08 14:41,
  // lastTransactionAt: 2021-11-19 09:16,
  // lastTransactionAmount: 0,
  // company: "Facebook Inc" or null,
  // description: "Scala enthusiast" or null,
  // email: "john.doe@example.com" or null,
) {
  def id = MemberId
  def slug = profile.substring("https://opencollective.com/".size)
  def link = website.getOrElse(twitter.getOrElse(github.getOrElse(profile)))
}

object OpenCollectiveMember {
  implicit val jsonReads: Reads[OpenCollectiveMember] = Json.reads[OpenCollectiveMember]
}