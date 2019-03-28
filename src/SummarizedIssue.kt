package com.geno1024.issue996

data class SummarizedIssue
(
    val created_at: String,
    val number: Int,
    val user_login: String,
    val user_id: Int,
    val title: String,
    val body: String
)