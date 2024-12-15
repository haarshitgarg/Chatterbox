# Protocol Documentation
<a name="top"></a>

## Table of Contents

- [messages.proto](#messages-proto)
    - [ChatMessage](#org-harshit-messenger-chat-ChatMessage)
    - [ChatMessageResponse](#org-harshit-messenger-chat-ChatMessageResponse)
    - [OnlineStatus](#org-harshit-messenger-chat-OnlineStatus)
  
    - [ChatService](#org-harshit-messenger-chat-ChatService)
  
- [Scalar Value Types](#scalar-value-types)



<a name="messages-proto"></a>
<p align="right"><a href="#top">Top</a></p>

## messages.proto



<a name="org-harshit-messenger-chat-ChatMessage"></a>

### ChatMessage
The message that actually goes to and fro between users


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| user | [string](#string) |  | User sending the message |
| friend | [string](#string) |  | User this message is intended for |
| message | [string](#string) |  | The actual text message |
| timestamp | [int64](#int64) |  | The timestamp this message was sent at |






<a name="org-harshit-messenger-chat-ChatMessageResponse"></a>

### ChatMessageResponse
Response message from the server.


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| deliveryStatus | [bool](#bool) |  |  |






<a name="org-harshit-messenger-chat-OnlineStatus"></a>

### OnlineStatus
Online status of the user


| Field | Type | Label | Description |
| ----- | ---- | ----- | ----------- |
| online | [bool](#bool) |  |  |





 

 

 


<a name="org-harshit-messenger-chat-ChatService"></a>

### ChatService
Service that handles sending messages to other users

| Method Name | Request Type | Response Type | Description |
| ----------- | ------------ | ------------- | ------------|
| SendMessage | [ChatMessage](#org-harshit-messenger-chat-ChatMessage) | [ChatMessageResponse](#org-harshit-messenger-chat-ChatMessageResponse) | Sends message to the server |
| StreamMessage | [ChatMessage](#org-harshit-messenger-chat-ChatMessage) stream | [ChatMessageResponse](#org-harshit-messenger-chat-ChatMessageResponse) | Stream messages to the server. The server responds at the end of the stream |
| SendMessagesGRPC | [ChatMessage](#org-harshit-messenger-chat-ChatMessage) stream | [ChatMessage](#org-harshit-messenger-chat-ChatMessage) stream | Stream messages to the server which server then forwards to the intended user |

 



## Scalar Value Types

| .proto Type | Notes | C++ | Java | Python | Go | C# | PHP | Ruby |
| ----------- | ----- | --- | ---- | ------ | -- | -- | --- | ---- |
| <a name="double" /> double |  | double | double | float | float64 | double | float | Float |
| <a name="float" /> float |  | float | float | float | float32 | float | float | Float |
| <a name="int32" /> int32 | Uses variable-length encoding. Inefficient for encoding negative numbers – if your field is likely to have negative values, use sint32 instead. | int32 | int | int | int32 | int | integer | Bignum or Fixnum (as required) |
| <a name="int64" /> int64 | Uses variable-length encoding. Inefficient for encoding negative numbers – if your field is likely to have negative values, use sint64 instead. | int64 | long | int/long | int64 | long | integer/string | Bignum |
| <a name="uint32" /> uint32 | Uses variable-length encoding. | uint32 | int | int/long | uint32 | uint | integer | Bignum or Fixnum (as required) |
| <a name="uint64" /> uint64 | Uses variable-length encoding. | uint64 | long | int/long | uint64 | ulong | integer/string | Bignum or Fixnum (as required) |
| <a name="sint32" /> sint32 | Uses variable-length encoding. Signed int value. These more efficiently encode negative numbers than regular int32s. | int32 | int | int | int32 | int | integer | Bignum or Fixnum (as required) |
| <a name="sint64" /> sint64 | Uses variable-length encoding. Signed int value. These more efficiently encode negative numbers than regular int64s. | int64 | long | int/long | int64 | long | integer/string | Bignum |
| <a name="fixed32" /> fixed32 | Always four bytes. More efficient than uint32 if values are often greater than 2^28. | uint32 | int | int | uint32 | uint | integer | Bignum or Fixnum (as required) |
| <a name="fixed64" /> fixed64 | Always eight bytes. More efficient than uint64 if values are often greater than 2^56. | uint64 | long | int/long | uint64 | ulong | integer/string | Bignum |
| <a name="sfixed32" /> sfixed32 | Always four bytes. | int32 | int | int | int32 | int | integer | Bignum or Fixnum (as required) |
| <a name="sfixed64" /> sfixed64 | Always eight bytes. | int64 | long | int/long | int64 | long | integer/string | Bignum |
| <a name="bool" /> bool |  | bool | boolean | boolean | bool | bool | boolean | TrueClass/FalseClass |
| <a name="string" /> string | A string must always contain UTF-8 encoded or 7-bit ASCII text. | string | String | str/unicode | string | string | string | String (UTF-8) |
| <a name="bytes" /> bytes | May contain any arbitrary sequence of bytes. | string | ByteString | str | []byte | ByteString | string | String (ASCII-8BIT) |

