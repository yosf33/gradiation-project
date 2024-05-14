package com.example.gradiationproject.screen.bottomNavigationScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.gradiationproject.R

@Composable
fun ProfileScreen() {
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color(android.graphics.Color.parseColor("#ececec"))),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout {
            val (topImg, profile) = createRefs()
            Image(painterResource(id = R.drawable.top_background), contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .constrainAs(topImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    })
            Image(painterResource(id = R.drawable.profile), contentDescription = null,
                Modifier.constrainAs(profile) {
                    top.linkTo(topImg.bottom)
                    bottom.linkTo(topImg.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

        }
        Text(
            text = "Youssef Soliman",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp),
            color = Color(android.graphics.Color.parseColor("#747679"))
        )

        Text(
            text = "youssif.soliman@gmail.com",
            fontSize = 20.sp,
            color = Color(android.graphics.Color.parseColor("#747679"))
        )
        
        Button(onClick = { /*TODO*/ },
            Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                .height(55.dp), colors = ButtonDefaults.buttonColors(backgroundColor =Color(android.graphics.Color.parseColor("#ffffff")) )
            ) {
            Column(
                modifier=Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(id = R.drawable.ic_1), contentDescription = "",modifier= Modifier
                    .padding(end = 5.dp)
                    .clickable { })
            }
            Column(modifier= Modifier
                .padding(start = 16.dp)
                .weight(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {

                Text(text = "My Reviews",color=Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

        }

        Button(onClick = { /*TODO*/ },
            Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                .height(55.dp), colors = ButtonDefaults.buttonColors(backgroundColor =Color(android.graphics.Color.parseColor("#ffffff")) )
        ) {
            Column(
                modifier=Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(id = R.drawable.ic_2), contentDescription = "",modifier= Modifier
                    .padding(end = 5.dp)
                    .clickable { })
            }
            Column(modifier= Modifier
                .padding(start = 16.dp)
                .weight(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {

                Text(text = "Account Settings",color=Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

        }

        Button(onClick = { /*TODO*/ },
            Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                .height(55.dp), colors = ButtonDefaults.buttonColors(backgroundColor =Color(android.graphics.Color.parseColor("#ffffff")) )
        ) {
            Column(
                modifier=Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(id = R.drawable.ic_3), contentDescription = "",modifier= Modifier
                    .padding(end = 5.dp)
                    .clickable { })
            }
            Column(modifier= Modifier
                .padding(start = 16.dp)
                .weight(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {

                Text(text = "My Reviews",color=Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

        }

        Button(onClick = { /*TODO*/ },
            Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                .height(55.dp), colors = ButtonDefaults.buttonColors(backgroundColor =Color(android.graphics.Color.parseColor("#ffffff")) )
        ) {
            Column(
                modifier=Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(id = R.drawable.ic_4), contentDescription = "",modifier= Modifier
                    .padding(end = 5.dp)
                    .clickable { })
            }
            Column(modifier= Modifier
                .padding(start = 16.dp)
                .weight(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {

                Text(text = "Notification",color=Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

        }

        
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}