# zd-count-tool
zd-count-tool
Today, Guo Dazhao asked me a question. If there are 10 million users, it is necessary to count their login status within 60 days, that is, whether to log in every day. How to deal with this, I think it is to mark the judgment by bit, he said that it can pass ssd The file offset is used as the user id, then each user allocates a space, 1k can be stored for more than 8,000 days, I realized it with java, I feel that the file access will be quite inefficient, ready to go home on weekends to see the go language Write one with go, it is estimated that it will compare ojbk.