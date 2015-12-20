# Call Flow for Registration

## Suppose

Client: 
	IP address: 192.168.168.228

Server;
	IP address: 192.168.172.254

## Flow

1. REGISTER -> 

	REGISTER sip:192.168.172.254;transport=udp SIP/2.0
	Call-ID: a7ee2d77df78754daf67bc68fdd80586@192.168.168.228
	CSeq: 389393 REGISTER
	From: "Default Company" <sip:441@192.168.172.254>;tag=88ad95e4
	To: "Default Company" <sip:441@192.168.172.254>
	Via: STP/2.0/UDP 192.168.168.228:5060;
	  branch=z9hG4bK59f2b8da506f41ee36e5e6cdf8a3c581;rport
	Max-Forwards: 70
	User-Agent: AlarmPoint SIP Client 4.0.0 r3644OM
	Expires: 3600
	Contact: "Default Company" <sip:441@192.168.168.228:5060;transport=udp>;expires=3600
	Content-Length: 0

2. 
