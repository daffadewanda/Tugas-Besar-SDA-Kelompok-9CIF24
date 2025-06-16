# 🏥 Daisuke Clinic - Sistem Manajemen Klinik Berbasis Java

> *Pernahkah kamu membayangkan bagaimana sebuah rumah sakit atau klinik mengelola ratusan data pasien, jadwal dokter, serta janji temu setiap harinya? Di balik layar, ada sistem yang memastikan semuanya berjalan rapi dan efisien. Tapi, seperti apa bentuk sistem itu sebenarnya?*

**Daisuke Clinic** adalah aplikasi Java yang dirancang untuk mensimulasikan proses manajemen klinik sederhana. Proyek ini menggabungkan konsep-konsep pemrograman berorientasi objek dan struktur data seperti **Linked List**, **Queue**, dan **Binary Search Tree (BST)** untuk mendemonstrasikan bagaimana data pasien, dokter, dan janji temu dapat dikelola secara terstruktur.

---

## 📁 Struktur Folder

```
Tubes1_revisi2/
├── src/ # Kode sumber Java
│ ├── com/daisukeclinic/ # Kelas utama
│ ├── com/daisukeclinic/model/ # Model data: pasien, dokter, dll
│ └── com/daisukeclinic/structure/ # Struktur data tambahan (Queue)
├── bin/ # File hasil kompilasi
├── patients.txt # Data pasien
├── doctors.txt # Data dokter
└── .vscode/ # Pengaturan VSCode
```


---

## 🧩 Fitur Utama

- ✅ Manajemen **pasien** menggunakan struktur **Linked List**
- ✅ Manajemen **dokter** dengan **Nested Linked List**
- ✅ Sistem **login dokter**
- ✅ Penjadwalan **janji temu pasien** menggunakan **Queue**
- ✅ Pencarian dan pengurutan pasien melalui **Binary Search Tree (BST)**
- ✅ Penyimpanan data secara eksternal ke file `.txt`

---

## 🧪 Fitur Program

1. Add New Patient  
2. Remove Patient by ID  
3. Search Patient by Name  
4. Display All Patients  
5. Doctor Login  
6. Doctor Logout  
7. View Last Logged-in Doctor  
8. Schedule Appointment  
9. Process Appointment  
10. Display Upcoming Appointments  
11. Search Patient by ID (BST)  
12. Display All Patients (BST Inorder)  
0. Exit

---

## 🚀 Cara Menjalankan

1. Clone atau download repositori ini
2. Buka proyek menggunakan Visual Studio Code atau IDE Java lainnya
3. Pastikan Java Development Kit (JDK 8+) telah terinstal
4. Jalankan file `Main.java` yang terletak di `src/com/daisukeclinic/`

---

## 📃 Langkah-langkah Berjalannya Program
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/0582c461-cdf7-4ea9-88dc-198003b286bb">
</p>
Memasuki antarmuka awal, kita dihadapkan dengan opsi-opsi kita mau masuk sebagai pasien, dokter, atau admin.


### 🕴Masuk Sebagai Pasien
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/97c61d4c-f684-4a68-b76a-df8e8e24463d">
</p>
Di sini, kita bisa melakukan perubahan dan pencarian terhadap data pasien, di antaranya kita bisa

1. Menambah Pasien Baru
2. Mencari Pasien Berdasarkan Nama
3. Mengajukan Pertemuan dengan dokter
4. Mencari ID Pasien


<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/ca527a77-d1a4-4e9f-8c57-66572f1a0644">
</p>
Ketika kita memilih opsi 1, yaitu Menambah Pasien Baru, kita akan diminta memasukkan ID, Nama, Umur, Alamat, dan Nomor Telepon dari Pasien.

<br/><br/>
<p align="center" width="100%">
    <img src="https://github.com/user-attachments/assets/453c0ce0-59fe-418f-9a9b-7cf1f902d267">
</p>
Ketika kita memilih opsi 2, yaitu Mencari Pasien Berdasarkan Nama, kita akan diminta memasukkan nama, lalu program akan memunculkan ID, Nama, Umur, Alamat, Nomor Telepon, dan Riwayat Medis.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/b8931f07-8f00-43e5-9753-4ef892eb8e57">
</p>
Ketika kita memilih opsi 3, tetapi dokter belum <i>login</i>, maka tidak bisa membuat pertemuan kepada dokter.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/787070a1-8378-405a-84be-9c48ba765683">
</p>
Ketika kita memilih opsi 3 dan dokter sudah <i>login</i>, maka perjanjian akan di-data. Masukkan ID Dokter yang dipilih, ID Pasien, dan Pukul Pertemuan.

<br/><br/>
<p align="center" width="100%">
    <img src="https://github.com/user-attachments/assets/4db741e6-2a49-46a0-91e3-d251ac08608c">
</p>
Ketika kita memilih opsi 4, kita diminta untuk memasukan ID Pasien untuk mencarinya. Setelah dimasukkan, program akan menampilkan detail dari pasien yang dicari.

### 🧑‍⚕️Masuk Sebagai Dokter
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/750a5c20-3b4b-4304-8dda-098405f6584d">
</p>
Saat memilih opsi Masuk Sebagai Dokter, harus memasukkan PIN untuk mengautentikasi bahwa yang masuk adalah dokter.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/fa37113a-42b6-430b-9feb-c12cd7a5cbba">
</p>
Di sini, kita bisa melakukan penambahan dokter baru serta media masuk dan keluarnya untuk keaktifan dokter.

1. Registrasi Dokter Baru
2. <i>Login</i> Dokter
3. <i>Logout</i> Dokter

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/81b43eab-5deb-4262-84e5-ae6b4d6bcaf3">
</p>
Ketika memilih opsi 1, kita akan diminta memasukan ID, Nama, Bidang Spesialis dari Dokter, dan <i>Shift</i> Kerjaannya.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/cb0c5522-8730-47e1-a13d-f1e903837897">
</p>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/b197944a-20a8-41d8-8221-1da88ce25365">
</p>
Setelah ditambahkan, maka akan ditunjukkan dokter-dokter yang sudah terdaftar, beserta ID, Spesialis, dan <i>Shift</i> Kerjaannya, sekaligus memberitahu bahwa dokter yang baru ditambahkan sudah berhasil ditambahkan.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/2aa1e7a9-e589-4e2c-949a-f7aa6f23166d">
</p>
Ketika kita memilih opsi 2, maka kita diminta memasukkan ID Dokter mana yang mau di-login-kan. Setelah <i>login</i>, artinya dokter itu sudah siap untuk berkerja dan dimintai janji temu.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/4a1397bb-f4d7-490b-aa78-37f6a499f4c8">
</p>

![image](https://github.com/user-attachments/assets/18d3b997-5fbe-438a-ae19-44f1bdea98d4)
![image](https://github.com/user-attachments/assets/91ffe018-5083-4346-865e-01f2bec02861)


![image](https://github.com/user-attachments/assets/c6898dee-35d0-4454-82a5-c942ae47c506)
![image](https://github.com/user-attachments/assets/3aa4d480-c6b4-4d49-a58a-b71bbc936b45)
![image](https://github.com/user-attachments/assets/cb864400-2b30-4296-a325-c1c3a71b6eb5)
![image](https://github.com/user-attachments/assets/74682d3e-5915-4071-9808-9b7aca3d01d0)
![image](https://github.com/user-attachments/assets/3685e5c7-c5a1-4bfd-ba07-0c16969caec2)
![image](https://github.com/user-attachments/assets/d456e7d7-7ec3-4375-ba33-d980563dbbe8)
![image](https://github.com/user-attachments/assets/07296705-366c-4c9a-baa2-f5011a4b5006)


![image](https://github.com/user-attachments/assets/b53684b3-a512-473e-8212-598b975e1f4e)











---
## ⚙️ Teknologi dan Konsep yang Digunakan

- 💻 Java 8+
- 🧱 OOP (Object-Oriented Programming)
- 🧮 Struktur Data:
  - Linked List (Pasien, Dokter)
  - Queue (Janji Temu)
  - Binary Search Tree (Pencarian Pasien)
- 🗂️ File I/O untuk penyimpanan data

---

## 👨‍💻 Kontribusi

- Arva Z
- Baihaqi Hakim Abdullah
- Fandhi 
- Wata
- Daffa dewanda

---

## 📄 Lisensi

Proyek ini hanya untuk keperluan pembelajaran dan akademik. Dilarang menggunakan secara komersial tanpa izin.
