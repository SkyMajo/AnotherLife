package com.nixo.afterworklife.MainPage.Data.MessageInfo.CommentList.NewData

data class CommentsItem(val isRead: Int = 0,
                        val replyId: Int = 0,
                        val updatedAt: String = "",
                        val explore: Explore,
                        val user_id: Int = 0,
                        val explore_id: Int = 0,
                        val createdAt: String = "",
                        val id: Int = 0,
                        val reply: String,
                        val user: User,
                        val content: String = "")