import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeTestTheme(darkTheme = false) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoginSignup1(isSignUp = false)
                }
            }
        }
    }
}

private val textFieldPadding1 = 32.dp
private val cornerRadius1 = 25.dp

// If isSignUp == true, show sign up form
// else, show login form
@Composable
fun LoginSignup1(isSignUp: Boolean, context: Context = LocalContext.current) {

    var textFullName by remember {
        mutableStateOf("")
    }
    var textPhone by remember {
        mutableStateOf("")
    }

    var textEmail by remember {
        mutableStateOf("")
    }

    var textPassword by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .background(Color(0xFFEEEEEE), RectangleShape)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = if (isSignUp) "Create Account" else "Welcome back!",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.roboto_bold, FontWeight.Bold)),
                    fontSize = 30.sp,
                    letterSpacing = 1.sp,
                    color = Color.Black
                )
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = if (isSignUp) "Sign up to get started" else "Sign in to your account",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
                    fontSize = 18.sp,
                    letterSpacing = 1.sp,
                    color = Color.Black
                )
            )

            // Full name text field
            if (isSignUp) {
                LoginSignup1TextFiled(
                    text = textFullName,
                    hint = "Full Name",
                    leadingIconRes = R.drawable.icon_person2
                ) {
                    textFullName = it
                }
            }


            // Email text filed
            LoginSignup1TextFiled(
                text = textEmail,
                hint = "Email",
                leadingIconRes = R.drawable.icon_email,
                onText = {
                    textEmail = it
                })


            // Phone text field
            if (isSignUp) {
                LoginSignup1TextFiled(
                    text = textPhone,
                    hint = "Phone Number",
                    leadingIconRes = R.drawable.icon_phone
                ) {
                    textPhone = it
                }
            }

            // Password text filed
            LoginSignup1TextFiled(
                text = textPassword,
                hint = "Password",
                leadingIconRes = R.drawable.icon_lock,
                onText = {
                    textPassword = it
                })

            // Sign in or Sign up button
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = textFieldPadding1,
                        end = textFieldPadding1,
                        top = textFieldPadding1
                    ),
                shape = RoundedCornerShape(cornerRadius1),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF35898f)),
                onClick = {
                    Toast.makeText(
                        context,
                        "Click: Button",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                Text(
                    text = if (isSignUp) "Sign Up" else "Sign In",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.roboto_medium, FontWeight.Medium)),
                        fontSize = 18.sp,
                        color = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider1()
                Text(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                    text = if (isSignUp) "Or Sign up with" else "Or Sign in with",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
                        fontSize = 16.sp,
                        letterSpacing = 1.sp,
                        color = Color.Black
                    )
                )
                Divider1()
            }

            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SignupWithIcons1(
                    iconRes = R.drawable.logo_google,
                    contentDescription = if (isSignUp) "Sign up with Google" else "Sign in with Google"
                )
                SignupWithIcons1(
                    iconRes = R.drawable.logo_facebook,
                    contentDescription = if (isSignUp) "Sign up with Facebook" else "Sign in with Facebook"
                )
            }
        }

        val textBottom1 = if (isSignUp) "Already a member? " else "Don’t have an account? "
        val textBottom2 = if (isSignUp) "Sign In" else "Sign Up"

        Row(
            modifier = Modifier.padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = textBottom1,
                color = Color.Black,
                fontFamily = FontFamily(
                    Font(
                        R.font.roboto_medium,
                        weight = FontWeight.Medium
                    )
                ),
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier.clickable {
                    Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
                },
                text = textBottom2,
                color = Color(0xFF35898f),
                fontFamily = FontFamily(
                    Font(
                        R.font.roboto_bold,
                        weight = FontWeight.Bold
                    )
                ),
                fontSize = 16.sp
            )

        }
    }
}

@Composable
private fun LoginSignup1TextFiled(
    text: String,
    hint: String,
    leadingIconRes: Int,
    onText: (String) -> Unit
) {

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = textFieldPadding1,
                end = textFieldPadding1,
                top = textFieldPadding1
            )
            .background(Color.White, RoundedCornerShape(cornerRadius1)),
        value = text,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF35898f),
            unfocusedBorderColor = Color.White,
            cursorColor = Color(0xFF35898f)
        ),
        onValueChange = {
            onText(it)
        },
        singleLine = true,
        shape = RoundedCornerShape(cornerRadius1),
        textStyle = loginSignup1TextFieldStyle(Color(0xFF35898f)),
        placeholder = {
            Text(
                text = hint,
                style = loginSignup1TextFieldStyle(Color(0xFF808080))
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIconRes),
                contentDescription = hint,
                tint = Color(0xFF35898f)
            )
        }
    )
}

@Composable
private fun SignupWithIcons1(iconRes: Int, contentDescription: String, context: Context = LocalContext.current) {
    OutlinedButton(
        modifier = Modifier.size(46.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(8.dp),
        border = BorderStroke(0.dp, Color.Transparent),
        elevation = ButtonDefaults.elevation(defaultElevation = 4.dp),
        onClick = {
            Toast.makeText(
                context,
                "Click",
                Toast.LENGTH_SHORT
            ).show()
        }) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = contentDescription,
            tint = Color.Unspecified
        )
    }
}

@Composable
private fun Divider1() {
    Divider(
        modifier = Modifier.width(64.dp),
        color = Color(0xFF333333),
        thickness = 1.dp
    )
}

@Composable
private fun loginSignup1TextFieldStyle(textColor: Color) = TextStyle(
    fontFamily = FontFamily(Font(R.font.roboto_medium, FontWeight.Medium)),
    letterSpacing = 1.sp,
    color = textColor
)