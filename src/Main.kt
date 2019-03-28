package com.geno1024.issue996

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import java.io.File
import java.net.URL

object Main
{
    private fun getIssueAtPage(page: Int) = URL("https://api.github.com/repos/996icu/996.ICU/issues?filter=all&page=$page&access_token=${Token.ACCESS_TOKEN}").openConnection().getInputStream().bufferedReader().readText()

    private fun getAllIssue() =
        (1..650).map {
            val tick = System.currentTimeMillis()
            File("./res/$it").writeText(getIssueAtPage(it))
            println("$it: ${System.currentTimeMillis() - tick}")
        }

    private fun analyseAllIssue() =
        File("./res/").listFiles().sortedWith(Comparator { o1, o2 -> o1.name.toInt() - o2.name.toInt() }).map {
            JSONArray.parseArray(
                it.readText()
            ).map { obj ->
                obj as JSONObject
                SummarizedIssue(obj["created_at"] as String, obj["number"] as Int, (obj["user"] as JSONObject)["login"] as String, (obj["user"] as JSONObject)["id"] as Int, obj["title"] as String, (obj["body"] as String).replace("\r", "").replace("\n", "\\n").replace("\t", "\\t") as String)
//                "${obj["created_at"]}\t${obj["number"]}\t${(obj["user"] as JSONObject)["login"]}\t${(obj["user"] as JSONObject)["id"]}\t${obj["title"]}\t${(obj["body"] as String).replace("\r", "").replace("\n", "\\n").replace("\t", "\\t")}"
            }
        }.flatten()

    @JvmStatic
    fun main(args: Array<String>)
    {
        println(analyseAllIssue().groupBy { it.user_login }.map { it.key to it.value.size }.sortedByDescending { it.second })
//        val r = JSONArray.parseArray(File("./res/1").readText())[0] as JSONObject
//        println(r.toJavaObject(Issue::class.java))
//        File("./result.tsv").writeText(analyseAllIssue().joinToString(separator = "\n") { it })
    }
}