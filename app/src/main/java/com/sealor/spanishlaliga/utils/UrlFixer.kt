package com.sealor.spanishlaliga.utils

/**
 * Complete incomplete string URLs
 */
class UrlFixer {

    companion object {
        fun fixUrl(url : String) : String{

            var newUrl : String = url
            if(!newUrl.startsWith("www.")) newUrl = "www.".plus(newUrl)
            if(!newUrl.startsWith("http://") || !url.startsWith("https://")) newUrl = "http://".plus(newUrl)

            return newUrl
        }
    }
}