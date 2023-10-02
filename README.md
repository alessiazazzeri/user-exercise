# Esercizio Gestione Utenti

## Requisiti di Sistema
* Java: JDK 17 o versione successiva
* Database: Configurazione di un database MySQL con nome db_userapp

## Istruzioni di installazione
1. Configurare il database:
    Modificare il file `application.properties` nella directory `src/main/resources` con le informazioni di connessione 
    al database desiderato
2. Eseguire l'applicazione utilizzando il comando della IDE o il wrapper Gradle:
   ```
   ./gradlew bootrun
    ```
   
L'applicazione sarà ora disponibile all'indirizzo http://localhost:8080.

### Elenco delle API

#### 1. Ottieni la Lista degli Utenti

**URL:** `/api/users`

**Metodo HTTP:** GET

**Descrizione:** Ottiene la lista completa degli utenti registrati nell'applicazione.

**Esempio di Richiesta:**

```http
GET /api/users
```

**Esempio di Risposta:**
```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "address": "123 Main St"
  },
  {
    "id": 2,
    "firstName": "Alice",
    "lastName": "Smith",
    "email": "alice.smith@example.com",
    "address": "456 Elm St"
  }
]
```

#### 2. Ottieni un Utente tramite ID

**URL:** `/api/users/{id}`

**Metodo HTTP:** GET

**Descrizione:** Ottiene un utente specifico in base all'ID fornito come parametro.

**Esempio di Richiesta:**

```http
GET /api/users/1
```

**Esempio di Risposta:**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "address": "123 Main St"
}
```

#### 3. Crea un Nuovo Utente

**URL:** `/api/users`

**Metodo HTTP:** POST

**Descrizione:** Crea un nuovo utente utilizzando i dati forniti nel corpo della richiesta.

**Esempio di Richiesta:**

```http
POST /api/users
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane.smith@example.com",
  "address": "789 Oak St"
}
```

**Esempio di Risposta:**
Se l'operazione di creazione ha successo, verrà restituito un codice di stato "201 Created" 
senza ulteriori dettagli nel corpo della risposta.

#### 4. Aggiorna un Utente Esistente

**URL:** `/api/users/{id}`

**Metodo HTTP:** PUT

**Descrizione:** Aggiorna un utente esistente in base all'ID fornito come parametro. 
I dati dell'utente da aggiornare vengono specificati nel corpo della richiesta.

**Esempio di Richiesta:**

```http
PUT /api/users/1
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Smith",
  "email": "john.smith@example.com",
  "address": "456 Elm St"
}
```

**Esempio di Risposta:**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Smith",
  "email": "john.smith@example.com",
  "address": "456 Elm St"
}
```

#### 5. Cancella un Utente

**URL:** `/api/users/{id}`

**Metodo HTTP:** DELETE

**Descrizione:** Cancella un utente esistente in base all'ID fornito come parametro.

**Esempio di Richiesta:**

```http
DELETE /api/users/1
```

**Esempio di Risposta:** 
Se l'operazione di creazione ha successo, verrà restituito un codice di stato "204 No content"
senza ulteriori dettagli nel corpo della risposta.

### 6. Importa Utenti da File CSV

**URL:** `/api/users/import`

**Metodo HTTP:** POST

**Descrizione:** Importa gli utenti da un file CSV fornito nel corpo della richiesta. 
Il file CSV deve contenere nome, cognome, email e indirizzo degli utenti

**Parametri della Richiesta:**
- `file` (Tipo: file CSV) - Il file CSV contenente i dati degli utenti.

**Esempio di Richiesta:**

```http
POST /api/users/import
Content-Type: multipart/form-data

[file] (file CSV): users.csv
```

