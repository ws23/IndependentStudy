# Call Flow for Registration

## Suppose

##### Client: 
	IP address: 192.168.168.228

##### Server:
	IP address: 192.168.172.254

## Flow

1. REGISTER -> 

```
	REGISTER sip:192.168.172.254;transport=udp SIP/2.0	

	Call-ID: a7ee2d77df78754daf67bc68fdd80586@192.168.168.228

	CSeq: 389393 REGISTER

	From: "Default Company" <sip:441@192.168.172.254>;tag=88ad95e4

	To: "Default Company" <sip:441@192.168.172.254>

	Via: STP/2.0/UDP 192.168.168.228:5060;branch=z9hG4bK59f2b8da506f41ee36e5e6cdf8a3c581;rport

	Max-Forwards: 70

	User-Agent: AlarmPoint SIP Client 4.0.0 r3644OM

	Expires: 3600

	Contact: "Default Company" <sip:441@192.168.168.228:5060;transport=udp>;expires=3600

	Content-Length: 0
```

2. 100 TRYING <-

```
	SIP/2.0 100 Trying

	Via: SIP/2.0/UDP 192.168.168.228:5060;received=192.168.168.228;branch=z9hG4bK59f2b8da506f41ee36e5e6cdf8a3c581;rport=5060

	From: "Default Company" <sip:441@192.168.172.254>;tag=88ad95e4

	To: "Default Company" <sip:441@192.168.172.254>	

	Call-ID: a7ee2d77df78754daf67bc68fdd80586@192.168.168.228

	CSeq: 389393 REGISTER

	User-Agent: Asterisk PBX

	Allow: INVITE,ACK,CANCEL,OPTIONS,BYE,REFER,SUBSCRIBE,NOTOFY

	Contact: <sip:441@192.168.172.254>

	Content-Length: 0
```

3. 401 UNAUTHORIZED <-

```
	SIP/2.0 401 Unauthorized
	
	Via: SIP/2.0/UDP 192.168.168.228:5060;received=192.168.168.228;branch=z9hG4bK59f2b8da506f41ee36e5e6cdf8a3c581;rport=5060

	From: "Default Company" <sip:441@192.168.172.254>;tag=88ad95e4

	To: "Default Company" <sip:441@192.168.172.254>;tag=as490c5009

	Call-ID: a7ee2d77df78754daf67bc68fdd80586@192.168.168.228

	User-Agent: Asterisk PBX

	Allow: INVITE,ACK,CANCEL,OPTIONS,BYE,REFER,SUBSCRIBE,NOTOFY

	WWW-Authenticate: Digest realm='asterisk",nonce="60f6cdd2",algorithm=MD5

	Content-Length: 0

```

4. REGISTER ->

```
	REGISTER sip:192.168.172.254;transport=udp SIP/2.0

	Call-ID: a7ee2d77df78754daf67bc68fdd80586@192.168.168.228

	From: "Default Company" <sip:441@192.168.172.254>;tag=88ad95e4

	To: "Default Company" <sip:441@192.168.172.254>

	Max-Forwards: 70

	User-Agent: AlarmPoint SIP Client 4.0.0 r3644OM

	Expires: 3600

	Contact: "Default Company" <sip:441@192.168.168.228:5060;transport=udp>;expires=3600

	Via: STP/2.0/UDP 192.168.168.228:5060;branch=z9hG4bK6a1306f8c0dda5b8bcd5b7b4230b910e

	Authorization: Digest uri="sip:192.168.172.254;transport=udp",realm="asterisk",username="441",nonce="60f6cdd2",algorithm=MD5,reponse="2f151a5627fc61af00eb47edf20a7257"

	CSeq: 389394 REGISTER
	
	Content-Length: 0

```

5. 100 TRYING <-

```
	SIP/2.0 100 Trying

	Via: SIP/2.0/UDP 192.168.168.228:5060;received=192.168.168.228;branch=z9hG4bK6a1306f8c0dda5b8bcd5b7b4230b910e

	From: "Default Company" <sip:441@192.168.172.254>;tag=88ad95e4

	To: "Default Company" <sip:441@192.168.172.254>	

	Call-ID: a7ee2d77df78754daf67bc68fdd80586@192.168.168.228

	CSeq: 389393 REGISTER

	User-Agent: Asterisk PBX

	Allow: INVITE,ACK,CANCEL,OPTIONS,BYE,REFER,SUBSCRIBE,NOTOFY

	Contact: <sip:441@192.168.172.254>

	Content-Length: 0
```

6. 200 OK <-

```
	SIP/2.0 200 OK
	
	Via: SIP/2.0/UDP 192.168.168.228:5060;received=192.168.168.228;branch=z9hG4bK6a1306f8c0dda5b8bcd5b7b4230b910e

	From: "Default Company" <sip:441@192.168.172.254>;tag=88ad95e4

	To: "Default Company" <sip:441@192.168.172.254>;tag=as490c5009

	Call-ID: a7ee2d77df78754daf67bc68fdd80586@192.168.168.228

	CSeq: 389394 REGISTER

	User-Agent: Asterisk PBX

	Allow: INVITE,ACK,CANCEL,OPTIONS,BYE,REFER,SUBSCRIBE,NOTOFY

	Expires: 3600

	Contact: <sip:441@192.168.172.254>;expires=3600

	Date: Fri, 15 Jan 2010 02:13:19 GMT

	Content-Length: 0
```
