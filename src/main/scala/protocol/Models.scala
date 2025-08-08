package protocol

import io.circe.Codec
import io.circe.generic.semiauto._

case class User(
  user_id: Long,
  name: Option[String],
  first_name: String,
  last_name: Option[String],
  username: Option[String],
  is_bot: Boolean,
  last_activity_time: Long
)
object User {
  implicit val codec: Codec[User] = deriveCodec
}

case class BotCommand(
  name: String,
  description: Option[String]
)
object BotCommand {
  implicit val codec: Codec[BotCommand] = deriveCodec
}

case class Image(url: String)
object Image { implicit val codec: Codec[Image] = deriveCodec }

case class UserWithPhoto(
  user_id: Long,
  name: Option[String],
  first_name: String,
  last_name: Option[String],
  username: Option[String],
  is_bot: Boolean,
  last_activity_time: Long,
  description: Option[String],
  avatar_url: Option[String],
  full_avatar_url: Option[String]
)
object UserWithPhoto {
  implicit val codec: Codec[UserWithPhoto] = deriveCodec
}

case class Recipient(
  chat_id: Option[Long],
  chat_type: String,
  user_id: Option[Long]
)
object Recipient { implicit val codec: Codec[Recipient] = deriveCodec }

case class MessageStat(views: Int)
object MessageStat { implicit val codec: Codec[MessageStat] = deriveCodec }

case class VideoThumbnail(url: String)
object VideoThumbnail { implicit val codec: Codec[VideoThumbnail] = deriveCodec }

case class AttachmentPayload(url: String)
object AttachmentPayload { implicit val codec: Codec[AttachmentPayload] = deriveCodec }

case class MediaAttachmentPayload(url: String, token: String)
object MediaAttachmentPayload { implicit val codec: Codec[MediaAttachmentPayload] = deriveCodec }

case class FileAttachmentPayload(url: String, token: String)
object FileAttachmentPayload { implicit val codec: Codec[FileAttachmentPayload] = deriveCodec }

case class PhotoAttachmentPayload(photo_id: Long, token: String, url: String)
object PhotoAttachmentPayload { implicit val codec: Codec[PhotoAttachmentPayload] = deriveCodec }

case class ContactAttachmentPayload(vcf_info: Option[String], max_info: Option[User])
object ContactAttachmentPayload { implicit val codec: Codec[ContactAttachmentPayload] = deriveCodec }

case class StickerAttachmentPayload(url: String, code: String)
object StickerAttachmentPayload { implicit val codec: Codec[StickerAttachmentPayload] = deriveCodec }

case class ShareAttachmentPayload(url: Option[String], token: Option[String])
object ShareAttachmentPayload { implicit val codec: Codec[ShareAttachmentPayload] = deriveCodec }

case class Keyboard(buttons: List[List[Button]])
object Keyboard { implicit val codec: Codec[Keyboard] = deriveCodec }

sealed trait Button {
  def `type`: String
  def text: String
}
object Button {
  implicit val codec: Codec[Button] = deriveCodec
}

case class CallbackButton(
  `type`: String,
  text: String,
  payload: String,
  intent: Option[String]
) extends Button
object CallbackButton { implicit val codec: Codec[CallbackButton] = deriveCodec }

case class LinkButton(
  `type`: String,
  text: String,
  url: String
) extends Button
object LinkButton { implicit val codec: Codec[LinkButton] = deriveCodec }

case class RequestGeoLocationButton(
  `type`: String,
  text: String,
  quick: Option[Boolean]
) extends Button
object RequestGeoLocationButton { implicit val codec: Codec[RequestGeoLocationButton] = deriveCodec }

case class RequestContactButton(
  `type`: String,
  text: String
) extends Button
object RequestContactButton { implicit val codec: Codec[RequestContactButton] = deriveCodec }

case class ChatButton(
  `type`: String,
  text: String,
  chat_title: String,
  chat_description: Option[String],
  start_payload: Option[String],
  uuid: Option[Long]
) extends Button
object ChatButton { implicit val codec: Codec[ChatButton] = deriveCodec }

sealed trait ReplyButton {
  def text: String
  def payload: Option[String]
  def `type`: String
}
object ReplyButton { implicit val codec: Codec[ReplyButton] = deriveCodec }

case class SendMessageButton(
  `type`: String,
  text: String,
  payload: Option[String],
  intent: Option[String]
) extends ReplyButton
object SendMessageButton { implicit val codec: Codec[SendMessageButton] = deriveCodec }

case class SendGeoLocationButton(
  `type`: String,
  text: String,
  payload: Option[String],
  quick: Option[Boolean]
) extends ReplyButton
object SendGeoLocationButton { implicit val codec: Codec[SendGeoLocationButton] = deriveCodec }

case class SendContactButton(
  `type`: String,
  text: String,
  payload: Option[String]
) extends ReplyButton
object SendContactButton { implicit val codec: Codec[SendContactButton] = deriveCodec }

sealed trait Attachment {
  def `type`: String
}
object Attachment { implicit val codec: Codec[Attachment] = deriveCodec }

case class PhotoAttachment(`type`: String, payload: PhotoAttachmentPayload) extends Attachment
object PhotoAttachment { implicit val codec: Codec[PhotoAttachment] = deriveCodec }

case class VideoAttachment(
  `type`: String,
  payload: MediaAttachmentPayload,
  thumbnail: Option[VideoThumbnail],
  width: Option[Int],
  height: Option[Int],
  duration: Option[Int]
) extends Attachment
object VideoAttachment { implicit val codec: Codec[VideoAttachment] = deriveCodec }

case class AudioAttachment(
  `type`: String,
  payload: MediaAttachmentPayload,
  transcription: Option[String]
) extends Attachment
object AudioAttachment { implicit val codec: Codec[AudioAttachment] = deriveCodec }

case class FileAttachment(
  `type`: String,
  payload: FileAttachmentPayload,
  filename: String,
  size: Long
) extends Attachment
object FileAttachment { implicit val codec: Codec[FileAttachment] = deriveCodec }

case class StickerAttachment(
  `type`: String,
  payload: StickerAttachmentPayload,
  width: Int,
  height: Int
) extends Attachment
object StickerAttachment { implicit val codec: Codec[StickerAttachment] = deriveCodec }

case class ContactAttachment(
  `type`: String,
  payload: ContactAttachmentPayload
) extends Attachment
object ContactAttachment { implicit val codec: Codec[ContactAttachment] = deriveCodec }

case class ShareAttachment(
  `type`: String,
  payload: ShareAttachmentPayload,
  title: Option[String],
  description: Option[String],
  image_url: Option[String]
) extends Attachment
object ShareAttachment { implicit val codec: Codec[ShareAttachment] = deriveCodec }

case class LocationAttachment(
  `type`: String,
  latitude: Double,
  longitude: Double
) extends Attachment
object LocationAttachment { implicit val codec: Codec[LocationAttachment] = deriveCodec }

case class InlineKeyboardAttachment(
  `type`: String,
  payload: Keyboard
) extends Attachment
object InlineKeyboardAttachment { implicit val codec: Codec[InlineKeyboardAttachment] = deriveCodec }

case class ReplyKeyboardAttachment(
  `type`: String,
  buttons: List[List[ReplyButton]]
) extends Attachment
object ReplyKeyboardAttachment { implicit val codec: Codec[ReplyKeyboardAttachment] = deriveCodec }

case class DataAttachment(
  `type`: String,
  data: String
) extends Attachment
object DataAttachment { implicit val codec: Codec[DataAttachment] = deriveCodec }

sealed trait MarkupElement {
  def `type`: String
  def from: Int
  def length: Int
}
object MarkupElement { implicit val codec: Codec[MarkupElement] = deriveCodec }

case class StrongMarkup(`type`: String, from: Int, length: Int) extends MarkupElement
object StrongMarkup { implicit val codec: Codec[StrongMarkup] = deriveCodec }

case class EmphasizedMarkup(`type`: String, from: Int, length: Int) extends MarkupElement
object EmphasizedMarkup { implicit val codec: Codec[EmphasizedMarkup] = deriveCodec }

case class MonospacedMarkup(`type`: String, from: Int, length: Int) extends MarkupElement
object MonospacedMarkup { implicit val codec: Codec[MonospacedMarkup] = deriveCodec }

case class LinkMarkup(`type`: String, from: Int, length: Int, url: String) extends MarkupElement
object LinkMarkup { implicit val codec: Codec[LinkMarkup] = deriveCodec }

case class StrikethroughMarkup(`type`: String, from: Int, length: Int) extends MarkupElement
object StrikethroughMarkup { implicit val codec: Codec[StrikethroughMarkup] = deriveCodec }

case class UnderlineMarkup(`type`: String, from: Int, length: Int) extends MarkupElement
object UnderlineMarkup { implicit val codec: Codec[UnderlineMarkup] = deriveCodec }

case class UserMentionMarkup(
  `type`: String,
  from: Int,
  length: Int,
  user_link: Option[String],
  user_id: Option[Long]
) extends MarkupElement
object UserMentionMarkup { implicit val codec: Codec[UserMentionMarkup] = deriveCodec }

case class HeadingMarkup(`type`: String, from: Int, length: Int) extends MarkupElement
object HeadingMarkup { implicit val codec: Codec[HeadingMarkup] = deriveCodec }

case class HighlightedMarkup(`type`: String, from: Int, length: Int) extends MarkupElement
object HighlightedMarkup { implicit val codec: Codec[HighlightedMarkup] = deriveCodec }

case class MessageBody(
  mid: String,
  seq: Long,
  text: Option[String],
  attachments: Option[List[Attachment]],
  markup: Option[List[MarkupElement]]
)
object MessageBody { implicit val codec: Codec[MessageBody] = deriveCodec }

case class LinkedMessage(
  `type`: String,
  sender: Option[User],
  chat_id: Option[Long],
  message: MessageBody
)
object LinkedMessage { implicit val codec: Codec[LinkedMessage] = deriveCodec }

case class Message(
  sender: Option[User],
  recipient: Recipient,
  timestamp: Long,
  link: Option[LinkedMessage],
  body: MessageBody,
  stat: Option[MessageStat],
  url: Option[String]
)
object Message { implicit val codec: Codec[Message] = deriveCodec }

case class Chat(
  chat_id: Long,
  `type`: String,
  status: String,
  title: Option[String],
  icon: Option[Image],
  last_event_time: Long,
  participants_count: Int,
  owner_id: Option[Long],
  participants: Option[Map[String, Long]],
  is_public: Boolean,
  link: Option[String],
  description: Option[String],
  dialog_with_user: Option[UserWithPhoto],
  messages_count: Option[Int],
  chat_message_id: Option[String],
  pinned_message: Option[Message]
)
object Chat { implicit val codec: Codec[Chat] = deriveCodec }

case class NewMessageLink(`type`: String, mid: String)
object NewMessageLink { implicit val codec: Codec[NewMessageLink] = deriveCodec }

case class PhotoAttachmentRequestPayload(
  url: Option[String],
  token: Option[String],
  photos: Option[Map[String, PhotoToken]]
)
object PhotoAttachmentRequestPayload { implicit val codec: Codec[PhotoAttachmentRequestPayload] = deriveCodec }

case class PhotoToken(token: String)
object PhotoToken { implicit val codec: Codec[PhotoToken] = deriveCodec }

case class UploadedInfo(token: String)
object UploadedInfo { implicit val codec: Codec[UploadedInfo] = deriveCodec }

case class ContactAttachmentRequestPayload(
  name: String,
  contact_id: Option[Long],
  vcf_info: Option[String],
  vcf_phone: Option[String]
)
object ContactAttachmentRequestPayload { implicit val codec: Codec[ContactAttachmentRequestPayload] = deriveCodec }

case class StickerAttachmentRequestPayload(code: String)
object StickerAttachmentRequestPayload { implicit val codec: Codec[StickerAttachmentRequestPayload] = deriveCodec }

case class InlineKeyboardAttachmentRequestPayload(buttons: List[List[Button]])
object InlineKeyboardAttachmentRequestPayload { implicit val codec: Codec[InlineKeyboardAttachmentRequestPayload] = deriveCodec }

sealed trait AttachmentRequest { def `type`: String }
object AttachmentRequest { implicit val codec: Codec[AttachmentRequest] = deriveCodec }

case class PhotoAttachmentRequest(`type`: String, payload: PhotoAttachmentRequestPayload) extends AttachmentRequest
object PhotoAttachmentRequest { implicit val codec: Codec[PhotoAttachmentRequest] = deriveCodec }

case class VideoAttachmentRequest(`type`: String, payload: UploadedInfo) extends AttachmentRequest
object VideoAttachmentRequest { implicit val codec: Codec[VideoAttachmentRequest] = deriveCodec }

case class AudioAttachmentRequest(`type`: String, payload: UploadedInfo) extends AttachmentRequest
object AudioAttachmentRequest { implicit val codec: Codec[AudioAttachmentRequest] = deriveCodec }

case class FileAttachmentRequest(`type`: String, payload: UploadedInfo) extends AttachmentRequest
object FileAttachmentRequest { implicit val codec: Codec[FileAttachmentRequest] = deriveCodec }

case class ContactAttachmentRequest(`type`: String, payload: ContactAttachmentRequestPayload) extends AttachmentRequest
object ContactAttachmentRequest { implicit val codec: Codec[ContactAttachmentRequest] = deriveCodec }

case class StickerAttachmentRequest(`type`: String, payload: StickerAttachmentRequestPayload) extends AttachmentRequest
object StickerAttachmentRequest { implicit val codec: Codec[StickerAttachmentRequest] = deriveCodec }

case class InlineKeyboardAttachmentRequest(`type`: String, payload: InlineKeyboardAttachmentRequestPayload) extends AttachmentRequest
object InlineKeyboardAttachmentRequest { implicit val codec: Codec[InlineKeyboardAttachmentRequest] = deriveCodec }

case class ReplyKeyboardAttachmentRequest(
  `type`: String,
  direct: Option[Boolean],
  direct_user_id: Option[Long],
  buttons: List[List[ReplyButton]]
) extends AttachmentRequest
object ReplyKeyboardAttachmentRequest { implicit val codec: Codec[ReplyKeyboardAttachmentRequest] = deriveCodec }

case class LocationAttachmentRequest(`type`: String, latitude: Double, longitude: Double) extends AttachmentRequest
object LocationAttachmentRequest { implicit val codec: Codec[LocationAttachmentRequest] = deriveCodec }

case class ShareAttachmentRequest(`type`: String, payload: ShareAttachmentPayload) extends AttachmentRequest
object ShareAttachmentRequest { implicit val codec: Codec[ShareAttachmentRequest] = deriveCodec }

case class NewMessageBody(
  text: Option[String],
  attachments: Option[List[AttachmentRequest]],
  link: Option[NewMessageLink],
  _notify: Option[Boolean],
  format: Option[String]
)
object NewMessageBody {
  implicit val codec: Codec[NewMessageBody] = Codec.forProduct5(
    "text",
    "attachments",
    "link",
    "notify",
    "format"
  )(NewMessageBody.apply)(nmb => (nmb.text, nmb.attachments, nmb.link, nmb._notify, nmb.format))
}

case class Callback(
  timestamp: Long,
  callback_id: String,
  payload: String,
  user: User
)
object Callback { implicit val codec: Codec[Callback] = deriveCodec }

sealed trait Update {
  def update_type: String
  def timestamp: Long
}
object Update { implicit val codec: Codec[Update] = deriveCodec }

case class MessageCallbackUpdate(
  update_type: String,
  timestamp: Long,
  callback: Callback,
  message: Option[Message],
  user_locale: Option[String]
) extends Update
object MessageCallbackUpdate { implicit val codec: Codec[MessageCallbackUpdate] = deriveCodec }

case class MessageCreatedUpdate(
  update_type: String,
  timestamp: Long,
  message: Message,
  user_locale: Option[String]
) extends Update
object MessageCreatedUpdate { implicit val codec: Codec[MessageCreatedUpdate] = deriveCodec }

case class MessageRemovedUpdate(
  update_type: String,
  timestamp: Long,
  message_id: String,
  chat_id: Long,
  user_id: Long
) extends Update
object MessageRemovedUpdate { implicit val codec: Codec[MessageRemovedUpdate] = deriveCodec }

case class MessageEditedUpdate(
  update_type: String,
  timestamp: Long,
  message: Message
) extends Update
object MessageEditedUpdate { implicit val codec: Codec[MessageEditedUpdate] = deriveCodec }

case class BotAddedToChatUpdate(
  update_type: String,
  timestamp: Long,
  chat_id: Long,
  user: User,
  is_channel: Boolean
) extends Update
object BotAddedToChatUpdate { implicit val codec: Codec[BotAddedToChatUpdate] = deriveCodec }

case class BotRemovedFromChatUpdate(
  update_type: String,
  timestamp: Long,
  chat_id: Long,
  user: User,
  is_channel: Boolean
) extends Update
object BotRemovedFromChatUpdate { implicit val codec: Codec[BotRemovedFromChatUpdate] = deriveCodec }

case class UserAddedToChatUpdate(
  update_type: String,
  timestamp: Long,
  chat_id: Long,
  user: User,
  inviter_id: Option[Long],
  is_channel: Boolean
) extends Update
object UserAddedToChatUpdate { implicit val codec: Codec[UserAddedToChatUpdate] = deriveCodec }

case class UserRemovedFromChatUpdate(
  update_type: String,
  timestamp: Long,
  chat_id: Long,
  user: User,
  admin_id: Option[Long],
  is_channel: Boolean
) extends Update
object UserRemovedFromChatUpdate { implicit val codec: Codec[UserRemovedFromChatUpdate] = deriveCodec }

case class BotStartedUpdate(
  update_type: String,
  timestamp: Long,
  chat_id: Long,
  user: User,
  payload: Option[String],
  user_locale: Option[String]
) extends Update
object BotStartedUpdate { implicit val codec: Codec[BotStartedUpdate] = deriveCodec }

case class ChatTitleChangedUpdate(
  update_type: String,
  timestamp: Long,
  chat_id: Long,
  user: User,
  title: String
) extends Update
object ChatTitleChangedUpdate { implicit val codec: Codec[ChatTitleChangedUpdate] = deriveCodec }

case class MessageChatCreatedUpdate(
  update_type: String,
  timestamp: Long,
  chat: Chat,
  message_id: String,
  start_payload: Option[String]
) extends Update
object MessageChatCreatedUpdate { implicit val codec: Codec[MessageChatCreatedUpdate] = deriveCodec }

