package com.example.gradiationproject.util

import androidx.annotation.DrawableRes
import com.example.gradiationproject.R

sealed class OnBoardingPage(
@DrawableRes
    val image:Int,
    val title:String,
    val description:String
){
    object First:OnBoardingPage(
        image = R.drawable.img_1,
        title= "World Wide",
        description ="Find projects from companies \n" +
                "everywhere in the word"

    )
object Second:OnBoardingPage(
        image = R.drawable.img_2,
        title= "Get Paid !",
        description ="Make money while working\n" +
                "on awesome projects"
    )
    object Third:OnBoardingPage(
        image = R.drawable.img_3,
        title= "Get Connected",
        description ="Chat with others freelancers\n" +
                "and develop your network"
    )
    object Fourth:OnBoardingPage(
        image = R.drawable.img_4,
        title= "Improve",
        description ="Work hard and level up!"

    )
    object Fifth:OnBoardingPage(
        image = R.drawable.img_5,
        title= "",
        description ="Enjoy your progress!"

    )

}
