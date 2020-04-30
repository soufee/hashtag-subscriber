package ci.ashamaz.hashtagsubscriber.util.extention

import ci.ashamaz.hashtagsubscriber.model.Channel
import ci.ashamaz.hashtagsubscriber.model.Message

fun Message.setLinks() {
    if (!this.link.isNullOrBlank()) {
        val prats = this.link!!.split("/")
        if (prats.size == 5) {
            val res = "https://t.me/s/${prats[3]}/${prats[4]}"
                    this.link!!.substring(0, this.link!!.lastIndexOf("/")) + "/s/" + prats.last()
            this.weblink = res
        }
    } else if (!this.weblink.isNullOrBlank()) {
        val prats = this.weblink!!.split("/")
        if (prats.size == 6) {
            this.link = "http://t.me/${prats[4]}/${prats[5]}"
        }
    }
}

fun Channel.setLinks() {
    if (!this.link.isNullOrBlank()) {
        val prats = this.link!!.split("/")
        if (prats.size == 4) {
            val res = this.link!!.substring(0, this.link!!.lastIndexOf("/")) + "/s/" + prats.last()
            this.weblink = res
        }
    } else if (!this.weblink.isNullOrBlank()) {
        val prats = this.weblink!!.split("/")
        if (prats.size == 5) {
            this.link = "http://t.me/${prats[3]}/${prats[4]}"
        }
    }
}