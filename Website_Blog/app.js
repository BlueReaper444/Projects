const express = require('express');
const expressLayouts = require('express-ejs-layouts');
const bodyParser = require('body-parser')
const cookieparser=require('cookie-parser');
var session=require('express-session');

const app = express();

const port = 4444;

app.use(cookieparser());
app.use(session({
secret:'secret',
resave:false,
saveUninitialized:false,
cookie:{
//maxAge:100000
}}));

var mysql = require('mysql2');
const { setServers } = require('dns');
const { time } = require('console');
const { fstat } = require('fs');

var con = mysql.createConnection({
  host: "localhost",
  user: "bluereaper444",
  password: "Parola1_0Mica"
});


const multer = require('multer');
const path = require('path');

// directorul 'views' va conține fișierele .ejs (html + js executat la server)
app.set('view engine', 'ejs');
// suport pentru layout-uri - implicit fișierul care reprezintă template-ul site-ului este views/layout.ejs
app.use(expressLayouts);
// directorul 'public' va conține toate resursele accesibile direct de către client (e.g., fișiere css, javascript, imagini)
app.use(express.static('public'))
// corpul mesajului poate fi interpretat ca json; datele de la formular se găsesc în format json în req.body
app.use(bodyParser.json());
// utilizarea unui algoritm de deep parsing care suportă obiecte în obiecte
app.use(bodyParser.urlencoded({ extended: true }));

// la accesarea din browser adresei http://localhost:4444/ se va returna textul 'Hello World'
// proprietățile obiectului Request - req - https://expressjs.com/en/api.html#req
// proprietățile obiectului Response - res - https://expressjs.com/en/api.html#res
app.get('/', (req, res) => {

	try{
		con.connect(function(err) {
			  if (err) throw err;
			 console.log("Connected!");
			con.query("USE warriorlibdb", function (err, result) {
				 if (err) throw err;
				 console.log("Database selected");
			 });

			 var sql = "SELECT PostDate,PostTitle,SUBSTRING(PostContent, 1, 300) as PostContent,PostCreator, PostCategory, PostImagePath, PostParentBlog From posts ORDER BY PostDate DESC;";
			 con.query(sql, function (err, result) {
				if (err) throw err;
				var sql = "SELECT PostTitle, PostImagePath FROM posts  ORDER BY PostViews DESC LIMIT 3;";
				con.query(sql, function (err2, result2) {
			   		if (err2) throw err2;	
		   		
				let user=req.session.user;
				if(!user)
					{
						res.clearCookie('name');
						res.clearCookie('mesajEroare');
						res.render('index',{posts: result, populars: result2});
					}
					else{
						res.render('index',{posts: result, populars: result2});
					}
				});
			});
			
		});
	}catch(e){
	  console.log(e);
		res.render('index',{posts: undefined});
	} 
});
app.get('/post', (req, res) => {
	let user=req.session.user;
	if(!user)
	{
		res.clearCookie('name');
		res.clearCookie('mesajEroare');
		res.cookie("mesajEroare","You must be loged in first.");
		res.redirect('autentificare');
	}
	else{
		res.render('post');
	}
});
  
var storage = multer.diskStorage({
	destination: function (req, file, cb) {
	  cb(null, __dirname+'/public/images')
	},
	filename: function (req, file, cb) {
	  cb(null, file.fieldname + '-' + Date.now() + path.extname(file.originalname))
	}
  })
   
var upload = multer({ storage: storage })
var imgupload = upload.fields([{name:'image'}]);

app.post('/do-post',imgupload, (req, res) => {
	let user=req.session.user;
	console.log("-------------");
	if(req.files){
		console.log('file uploaded');
		console.log(req.files.image[0].filename);
	}
	if(!user)
	{
		res.clearCookie('name');
		res.clearCookie('mesajEroare');
		res.render('autentificare');
	}
	else{
	  	try{
			con.connect(function(err) {
		  		if (err) throw err;
		 		console.log("Connected!");
				con.query("USE warriorlibdb", function (err, result) {
		 			if (err) throw err;
		 			console.log("Database selected");
		 		});
				console.log(req.body.title);
				console.log(req.body.content);
				 var sql = "SELECT * From posts WHERE PostTitle='"+req.body.title+"';";
		 		con.query(sql, function (err, result) {
					if (err) throw err;
					console.log(result);
					console.log(result.toString);
					if(result=='[]'){
						console.log("Post already exists!");
						res.render('do-post',{rezultat: "Failed to add post.. It already exists"});
					}
					else{
						let currentTime = new Date();
						let cDay = currentTime.getDate()
						let cMonth = currentTime.getMonth() + 1
						let cYear = currentTime.getFullYear()
						let currentDate = cYear+'-'+cMonth+'-'+cDay;
						console.log(currentDate)
						var sql = "INSERT IGNORE INTO posts (PostDate, PostTitle, PostCategory, PostContent, PostCreator, PostImagePath, PostKeywords, PostParentBlog,PostViews) VALUES (NOW(),'"+req.body.title+"','"+req.body.category+"','"+req.body.content+"','"+user+"','"+req.files.image[0].filename+"','"+req.body.keywords+"','"+req.body.parentblog+"','0');";
						con.query(sql, function (err, result) {
						   if (err) throw err;
						   console.log("New post inserted");
						   console.log(result);
						   res.render('do-post',{rezultat: "Post successfully added.."});
						   console.log(req.body.imagePath);
					   });
					}
				});
			});
		}catch(e){
		  console.log(e);
		  res.render('do-post',{rezultat: "Failed to add post.."});
		  
		} 
	}
});

app.get('/autentificare', (req, res) => {

	let user=req.session.user;
	console.log(user)
	if(user){
		res.clearCookie('mesajEroare');
		  res.redirect('/');
	}
	else{
	  res.render('autentificare', {page: "Autentification"});
	}
  });
  
  app.post('/verificare-autentificare', (req, res) => {
  
	let sess = req.session;
	  try{
		con.connect(function(err) {
		  if (err) throw err;
		 console.log("Connected!");
		 con.query("USE warriorlibdb", function (err, result) {
		  if (err) throw err;
		  console.log("Database selected");
		  });
		  var sql = "SELECT * FROM users WHERE UserName = ?";
		  con.query(sql,[req.body.username], function (err, result) {
			if (err) throw err;
			console.log(String(result));
			if(String(result)!=''&&req.body.password==result[0].UserPassword){
				console.log("User found");
			 	if(result[0].usertype=="admin"){
					sess.nivel="admin";
			  	}
			  	else{
					sess.nivel="user";
			  	}
			  	res.cookie("name",result[0].UserName);
			  	res.clearCookie("mesajEroare");
			  	sess.user=result[0].UserName;
			  	console.log(sess.user);
			  	res.redirect( '/');
			}
		 	 else{
			res.cookie("mesajEroare","Invalid user or password. Please try again.");
			res.redirect( '/autentificare');
		  }
		  });
		});
		}catch(e){
		  console.log(e);
		  res.cookie("mesajEroare","Problema la server");
		  res.redirect( 'http://localhost:4444/autentificare');
		}

  });

  app.get("/logout",(req, res)=>{
	  console.log(req.session)
	req.session.destroy(function(err) {
	  res.clearCookie('name');
	  res.clearCookie('mesajEroare');
	  res.redirect("/")
	});
  });


app.get("/posts/:id", function (req,res){
	try{
		console.log(req.params.id)
		con.connect(function(err) {
			  if (err) throw err;
			 console.log("Connected!");
			con.query("USE warriorlibdb", function (err, result) {
				 if (err) throw err;
				 console.log("Database selected");
			 });

			 var sql ="SELECT * From posts WHERE PostTitle='"+req.params.id+"';";
			 con.query(sql, function (err, result) {
				if (err) throw err;
				console.log(result)
				let user=req.session.user;
				if(!user)
				{
					res.clearCookie('name');
					res.render('posts',{post: result});
				}
				else{
					res.render('posts',{post: result});
				}
				var sql ="UPDATE posts SET PostViews=PostViews+1 WHERE PostTitle='"+req.params.id+"';";
				con.query(sql, function (err, result) {
					if (err) throw err;
						console.log("Another Page View")
				});
			});
		});
	}catch(e){
	  console.log(e);
	  res.redirect('/404');
	} 
});

app.get('/user', function(req, res){
	let user=req.session.user;
	if(!user)
	{
		res.clearCookie('name');
		res.clearCookie('mesajEroare');
		res.cookie("mesajEroare","You must be loged in first.");
		res.redirect('autentificare');
	}
	else{
		res.render('user');
	}
});

app.get('/deletePost', function(req, res){
	let user=req.session.user;
	if(!user)
	{
		res.clearCookie('name');
		res.clearCookie('mesajEroare');
		res.cookie("mesajEroare","You must be loged in first.");
		res.redirect('autentificare');
	}
	else{
		res.render('deletePost');
	}
});

app.post('/delete-post', (req, res) => {
	let user=req.session.user;
	console.log("-------------");
	console.log(req.body.title);
	if(!user)
	{
		res.clearCookie('name');
		res.clearCookie('mesajEroare');
		res.render('autentificare');
	}
	else{
	  	try{
			con.connect(function(err) {
		  		if (err) throw err;
		 		console.log("Connected!");
				con.query("USE warriorlibdb", function (err, result) {
		 			if (err) throw err;
		 			console.log("Database selected");
		 		});
				 var sql = "DELETE FROM posts WHERE PostTitle='"+req.body.title+"';";
		 		con.query(sql, function (err, result) {
					if (err) throw err;
					console.log(result);
					res.render('delete-post',{rezultat: "Post deleted successful.."});
					
				});
			});
		}catch(e){
		  console.log(e);
		  res.send("Failed to delete post..");
		  res.render('delete-post',{rezultat: "Failed to delete post.."});
		} 
	}
});

app.get("/category/:id", function (req,res){
	try{
		console.log(req.params.id)
		con.connect(function(err) {
			  if (err) throw err;
			 console.log("Connected!");
			con.query("USE warriorlibdb", function (err, result) {
				 if (err) throw err;
				 console.log("Database selected");
			 });

			 var sql = "SELECT PostDate,PostTitle,SUBSTRING(PostContent, 1, 300) as PostContent,PostCreator, PostCategory, PostImagePath, PostParentBlog From posts WHERE PostCategory='"+req.params.id+"' ORDER BY PostDate DESC;";
			 con.query(sql, function (err, result) {
				if (err) throw err;
				console.log(result)
				let user=req.session.user;
				if(!user)
				{
					res.clearCookie('name');
					res.render('category',{posts: result,category: req.params.id});
				}
				else{
					res.render('category',{posts: result,category: req.params.id});
				}
			});
		});
	}catch(e){
	  console.log(e);
	  res.redirect('/404');
	} 
});

app.get("/specificuser/:id", function (req,res){
	try{
		console.log(req.params.id)
		con.connect(function(err) {
			  if (err) throw err;
			 console.log("Connected!");
			con.query("USE warriorlibdb", function (err, result) {
				 if (err) throw err;
				 console.log("Database selected");
			 });
			 var sql = "SELECT PostDate,PostTitle,SUBSTRING(PostContent, 1, 300) as PostContent, PostCategory, PostImagePath, PostParentBlog From posts WHERE PostCreator='"+req.params.id+"' ORDER BY PostDate DESC;";
			 con.query(sql, function (err, result) {
				if (err) throw err;
				console.log(result)
				var sql = "SELECT UserDescription,UserName From users WHERE UserName='"+req.params.id+"';";
				con.query(sql, function (err, result2) {
				   if (err) throw err;
				   console.log(result2)
				   let user=req.session.user;
				if(!user)
				{
					res.clearCookie('name');
					res.render('specificuser',{posts: result,user: result2});
				}
				else{
					res.render('specificuser',{posts: result,user: result2});
				}
			   });
			});
		});
	}catch(e){
	  console.log(e);
	  res.redirect('/404');
	} 
});

app.get('/contact', function(req, res){
	res.render('contact');
});

app.get('/404', function(req, res){
	res.render('404');
});
  
app.get('*', function(req, res){
	res.redirect('/404');
});

app.listen(port, () => console.log(`Serverul rulează la adresa http://localhost:`));