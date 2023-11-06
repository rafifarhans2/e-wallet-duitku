# Aplikasi Duitku Dompet Digital

### Deskripsi
Aplikasi dompet digital Duitku adalah salah satu solusi modern yang memudahkan pengguna dalam mengelola keuangan mereka. Dengan fitur-fitur yang canggih dan user-friendly, Duitku memungkinkan pengguna untuk melakukan berbagai transaksi keuangan secara mudah dan aman. Berikut adalah deskripsi singkat mengenai aplikasi Duitku:

Manajemen Keuangan: Aplikasi Duitku memungkinkan pengguna untuk mengatur dan melacak semua aspek keuangan mereka dalam satu tempat. Pengguna dapat memantau saldo, catat transaksi, dan mengelompokkan pengeluaran serta pemasukan untuk memahami bagaimana uang mereka digunakan.

Dompet Digital: Duitku memungkinkan pengguna untuk menyimpan uang mereka dalam bentuk digital, sehingga mereka tidak perlu selalu membawa uang tunai. Dengan dompet digital, pengguna dapat melakukan pembayaran, transfer uang, dan bahkan berbelanja online dengan lebih mudah.

Transaksi Cepat: Aplikasi ini memungkinkan pengguna untuk melakukan transaksi keuangan dengan cepat. Mereka dapat mentransfer uang ke rekan kerja, teman, atau keluarga dengan mudah menggunakan nomor ponsel atau alamat email mereka.

Pembayaran Tagihan: Duitku juga memungkinkan pengguna untuk membayar tagihan secara online, termasuk tagihan listrik, air, telepon, internet, dan lainnya. Ini memudahkan pengguna untuk mengelola pembayaran rutin mereka tanpa harus mengunjungi kantor pos atau berdiri dalam antrian panjang.

Riwayat Transaksi: Aplikasi ini menyediakan riwayat transaksi yang terperinci, sehingga pengguna dapat melihat semua transaksi yang telah mereka lakukan. Ini membantu pengguna untuk melacak pengeluaran mereka dan membuat perencanaan keuangan yang lebih baik.

Top-Up dan Penarikan Tunai: Duitku juga memungkinkan pengguna untuk melakukan top-up saldo mereka dengan mudah melalui transfer bank atau melalui outlet-outlet yang bekerja sama. Pengguna juga dapat menarik uang tunai dari dompet digital mereka di mesin ATM yang terkait.

Aplikasi Duitku merupakan alat yang sangat berguna untuk mengelola keuangan pribadi, melakukan transaksi dengan mudah, dan menghemat waktu dalam kegiatan sehari-hari. Dengan fitur-fitur yang lengkap dan fokus pada keamanan, Duitku dapat menjadi solusi yang praktis bagi siapa saja yang ingin mengatur keuangan mereka secara efisien.


Fitur-fitur aplikasi duitku adalah:

1. Manajemen 
2. Manajemen 
3. Manajemen 
4. Laporan Transaksi

# User API Spec

## Register User

Endpoint : POST api/auth/register

Request Body :

````json
{
  "email" : "nama@gmail.com",
  "mobilePhone": "0851778876",
  "password" : "rahasia"
}
````

Response Body (Created, 201) :

````json
{
  "data" : "Created"
}
````

Response Body (Failed) :

````json
{
  "errors" : "String"
}
````

## Login User

Endpoint : POST api/auth/login

Request Body :

````json
{
  "email" : "nama@gmail.com",
  "password" : "rahasia"
}

````

Response Body (Ok, 200) :

````json
{
  "data" : {
    "token" : "TOKEN",
    "expiredAt" : 2342342344321 
  }
}
````

Response Body (Failed, 401) :

````json
{
  "errors" : "String"
}
````
## Get User

Endpoint : GET /api/auth/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

````json
{
  "data" : {
    "email" : "nama@gmail.com",
    "name" : "Nama Lengkap",
    "address" : "Jalan  Raya",
    "mobilePhone" : "086564772"
  }
}
````

Response Body (Failed, 401) :

````json
{
  "errors" : "Unauthorized"
}
````

## Update User

Endpoint : PUT /api/auth/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

````json
{
  "email" : "nama@gmail.com", 
  "password" : "new password",
  "name" : "Nama Lengkap",
  "address" : "Jalan  Raya",
  "mobilePhone" : "086564772"
}
````

Response Body (Success) :

````json
{
  "data" : {
    "email" : "nama@gmail.com",
    "name" : "Nama Lengkap",
    "address" : "Jalan  Raya",
    "mobilePhone" : "086564772"
  }
}
````

Response Body (Failed, 401) :

````json
{
  "errors" : "Unauthorized"
}
````

## Logout User

Endpoint : DELETE /api/auth/logout

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

````json
{
  "data" : "OK"
}
````