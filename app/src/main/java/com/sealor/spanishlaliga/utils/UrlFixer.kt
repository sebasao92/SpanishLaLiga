package com.sealor.spanishlaliga.utils

class UrlFixer {

    companion object {
        fun fixUrl(url : String) : String{

            var newUrl : String = url;
            if(!url.startsWith("www.")) newUrl = "www.".plus(url)
            if(!url.startsWith("http://") || !url.startsWith("https://")) newUrl = "http://".plus(url)

            return newUrl
        }
    }
}