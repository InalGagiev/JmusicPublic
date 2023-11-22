# Documentation Api

## Registaration and authorization
### этот запрос позволить создать пользователя

POST: /api/auth/registration
{
    username: "имя пользователя",
    password: "пароль",
    confirmPassword: "подтверждение пароля",
    email: "почта"
}

Ответ:
пользователь успешно создан


### этот запрос войти в систему получив токен
POST: /api/auth/authorization
{  
"username": "username",   
"password": "password" 
}

Ответ:
{ 
"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpbmFsIiwicm9sZXMiOltdLCJleHAiOjYwMDAwMDE2OTg2OTE1MzEsImlhdCI6MTY5ODY5MTUzMX0.-9t8_-mI1pXt5OnAwwg-vc4Sa5JzUAnEqqrdot0DnNE"
}


## Music Crud 
### Запрос на получение трека по называнию

GET: /get-music/{musicname}

Ответ:  
{   
"release_date": date,    
"likes": "",     
"dislikes": "",    
"album": "",    
"name": "",  
"path": "",  
"artist": ""  
}

### запрос на создание песни

POST: api/music/create-music

Запрос:   
{    
"path": "",   
"name": "",   
"artist": "",   
"album": ""    
}   

Ответ:
{  
"id": 21,   
"titleRu": null,  
"name": "",  
"artist": 
    {    
        "id": 1,  
        "name": "",  
        "album": null,   
        "biography": ""   
    },    
"album":    
    {       
        "id": 1,   
        "name": "",   
        "artist": {   
        "id": 1,  
        "name": "",   
        "album": ,   
        "biography": ""    
    }   
},      
"likes": null,  
"path": "",  
"dislikes": null,  
"releaseDate": null   
}  

### запрос на Обновление данных о песне

PUT: api/music/update-music/{musicName}

Запрос:   
{    
"path": "",     
"name": "",     
"artist": "",     
"album": ""      
}   
  
Ответ:  
песня успешно обновлена  


### запрос на Обновление данных о песне
DELETE: /api/music/music-delete/{music}

Ответ:
песня успешно удалена


## PlaylistCrud
### добавление песни в плейлист
POST: /playlist-music-add   
запрос:    
}      
    playlist: "";      
    music: "";     
}   

Ответ:    
"Айди плейлиста: , Айди песни: "    

### Удаление песни из плейлиста    
DELETE: /playlist-music-delete    
запрос:   
}    
    playlist: "";    
    music: "";     
}    

Ответ:   
песня успешно удалена из плейлиста    
### Получение плейлистов пользователя    
на этот маршрут нужно переходить авторизованным, тоесть   
в хэдэрах Authorization: токен    
GET: /users/playlists     

ответ:    
список плей листов    

### содание плей листа
POST: /create-playlist   

запрос:   
{   
    name: ""   
}   

ответ:   
плейлист успешно создан     
 
### обновление плейлиста
PUT: /update-playlist/{username}    

запрос:    
{     
    name: ""    
}     
   
Ответ:    
плейлист успешно обновлен     


### Удаление плейлиста

DELETE: /playlist-delete/{playlist}       

Ответ:       
плей лист успешно удален   


## AlbumCrud    
### получение альбома    

GET: /api/album/{albumName}     

ответ:  
   
{    
id: ,   
name: "",   
music: "",   
artist: "",   
}   


### создание альбома
запрос:   
POST: /api/album   
{   
 name: "",    
}   
ответ:    
альбом успешно создан   


### обновление альбома
запрос:
PUT: /api/album/{albumName}

{    
id: ,   
name: "",   
music: "",   
artist: "",   
}   

Ответ:
альбом успешно обновлен

### удаление альбома 
запрос:
DELETE: /api/album/{albumName}

ответ:
пользователь успешно удален