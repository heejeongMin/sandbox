package com.example.sandbox.application

import org.springframework.stereotype.Service

@Service
class IsItFridayYet {

    fun isItFriday(today: String): String {
        return if (today == "Friday") "TGIF" else "Nope"
    }
}