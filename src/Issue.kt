package com.geno1024.issue996

data class Issue
(
    var url: String,
    var repository_url: String,
    var labels_url: String,
    var comments_url: String,
    var events_url: String,
    var html_url: String,
    var id: Long,
    var node_id: String,
    var number: Int,
    var title: String,
    var user: User,
    var labels: Array<String>,
    var state: String,
    var locked: Boolean,
    var assignee: String,
    var assignees: Array<String>,
    var milestone: String,
    var comments: Int,
    var created_at: String,
    var updated_at: String,
    var closed_at: String?,
    var author_association: String,
    var body: String
)