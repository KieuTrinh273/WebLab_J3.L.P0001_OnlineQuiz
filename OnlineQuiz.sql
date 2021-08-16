USE [master]
GO
/****** Object:  Database [LabWeb_Onlinequiz]    Script Date: 7/22/2021 7:20:33 AM ******/
CREATE DATABASE [LabWeb_Onlinequiz]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'LabWeb_Onlinequiz', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\LabWeb_Onlinequiz.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'LabWeb_Onlinequiz_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\LabWeb_Onlinequiz_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LabWeb_Onlinequiz].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET ARITHABORT OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET  ENABLE_BROKER 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET RECOVERY FULL 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET  MULTI_USER 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET DB_CHAINING OFF 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'LabWeb_Onlinequiz', N'ON'
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET QUERY_STORE = OFF
GO
USE [LabWeb_Onlinequiz]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 7/22/2021 7:20:34 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[role] [varchar](20) NULL,
	[email] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[history]    Script Date: 7/22/2021 7:20:34 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[history](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userID] [int] NOT NULL,
	[point] [float] NOT NULL,
	[date] [date] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 7/22/2021 7:20:34 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[question] [varchar](max) NULL,
	[option1] [varchar](max) NULL,
	[option2] [varchar](max) NULL,
	[option3] [varchar](max) NULL,
	[option4] [varchar](max) NULL,
	[date] [date] NULL,
	[answer] [varchar](max) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 
GO
INSERT [dbo].[Account] ([id], [username], [password], [role], [email]) VALUES (1, N'teacher1', N'123', N'teacher', N'teacher1@gmail.com')
GO
INSERT [dbo].[Account] ([id], [username], [password], [role], [email]) VALUES (2, N'student1', N'123', N'normal', N'student1@gmail.com')
GO
INSERT [dbo].[Account] ([id], [username], [password], [role], [email]) VALUES (3, N'teacher2', N'123', N'teacher', N'teacher2@gmail.com')
GO
INSERT [dbo].[Account] ([id], [username], [password], [role], [email]) VALUES (4, N'student2', N'123', N'normal', N'student2@gmail.com')
GO
INSERT [dbo].[Account] ([id], [username], [password], [role], [email]) VALUES (5, N'teacher3', N'123', N'teacher', N'teacher3@gmail.com')
GO
INSERT [dbo].[Account] ([id], [username], [password], [role], [email]) VALUES (6, N'Anna', N'123', N'student', N'anna@gmail.com')
GO
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
SET IDENTITY_INSERT [dbo].[Question] ON 
GO
INSERT [dbo].[Question] ([ID], [question], [option1], [option2], [option3], [option4], [date], [answer]) VALUES (1, N'Which of the following best describes integration testing?', N'Testing performed to expose faults in the interfaces and in the interaction between integrated components.', N'Testing to verify that a component is ready for integration.', N'Testing to verify that the test environment can be integrated with the product.', N'Integration of automated software test suites with the product.', CAST(N'2021-07-20' AS Date), N'1')
GO
INSERT [dbo].[Question] ([ID], [question], [option1], [option2], [option3], [option4], [date], [answer]) VALUES (2, N'2 answers are correct', N'Correct', N'Incorrect', N'Correct', N'Incorrect', CAST(N'2021-07-20' AS Date), N'13')
GO
INSERT [dbo].[Question] ([ID], [question], [option1], [option2], [option3], [option4], [date], [answer]) VALUES (3, N'According to the ISTQB Glossary, debugging:', N'Is part of the fundamental testing process.', N'Includes the repair of the cause of a failure.', N'Involves intentionally adding known defects.', N'Follows the steps of a test procedure.', CAST(N'2021-07-20' AS Date), N'2')
GO
INSERT [dbo].[Question] ([ID], [question], [option1], [option2], [option3], [option4], [date], [answer]) VALUES (4, N'Which of the following could be a root cause of a defect in financial software in which an incorrect interest rate is calculated?', N'Insufficient funds were available to pay the interest rate calculated.', N'Insufficient calculations of compound interest were included.', N'Insufficient training was given to the developers concerning compound interest alculation rules.', N'Inaccurate calculators were used to calculate the expected results.', CAST(N'2021-07-20' AS Date), N'3')
GO
INSERT [dbo].[Question] ([ID], [question], [option1], [option2], [option3], [option4], [date], [answer]) VALUES (11, N'ABCD', N'A', N'B', N'C', N'D', CAST(N'2021-07-20' AS Date), N'34')
GO
INSERT [dbo].[Question] ([ID], [question], [option1], [option2], [option3], [option4], [date], [answer]) VALUES (13, N'Which two specification-based testing techniques are most closely related to each other?', N'Decision tables and state transition testing', N'Equivalence partitioning and state transition testing', N'Decision tables and boundary value analysis', N'Equivalence partitioning and boundary value analysis', CAST(N'2021-07-20' AS Date), N'4')
GO
INSERT [dbo].[Question] ([ID], [question], [option1], [option2], [option3], [option4], [date], [answer]) VALUES (6, N'What does it mean if a set of tests has achieved 90% statement coverage?', N'9 out of 10 decision outcomes have been exercised by this set of tests.', N'9 out of 10 statements have been exercised by this set of tests.', N'9 out of 10 tests have been run on this set of software.', N'9 out of 10 requirements statements about the software are correct.', CAST(N'2021-07-20' AS Date), N'2')
GO
INSERT [dbo].[Question] ([ID], [question], [option1], [option2], [option3], [option4], [date], [answer]) VALUES (7, N'A test plan is written specifically to describe a level of testing where the primary goal is establishing confidence in the system. Which of the following is a likely name for this document?', N'Master test plan', N'System test plan', N'Acceptance test plan', N'Project plan', CAST(N'2021-07-20' AS Date), N'3')
GO
INSERT [dbo].[Question] ([ID], [question], [option1], [option2], [option3], [option4], [date], [answer]) VALUES (8, N'What is the best description of static analysis?', N'The analysis of batch programs', N'The reviewing of test plans', N'The analysis of program code or other software artifacts', N'The use of black-box testing', CAST(N'2021-07-20' AS Date), N'3')
GO
INSERT [dbo].[Question] ([ID], [question], [option1], [option2], [option3], [option4], [date], [answer]) VALUES (9, N'System test execution on a project is planned for eight weeks. After a week of testing, a tester suggests that the test objective stated in the test plan of finding as many defects as possible during system testâ?? might be more closely met by redirecting the test effort according to which test principle?', N'Impossibility of exhaustive testing.', N'Importance of early testing.', N'The absence of errors fallacy.', N'Defect clustering.', CAST(N'2021-07-20' AS Date), N'4')
GO
INSERT [dbo].[Question] ([ID], [question], [option1], [option2], [option3], [option4], [date], [answer]) VALUES (10, N'ABC?', N'A', N'B', N'C', N'D', CAST(N'2021-07-20' AS Date), N'123')
GO
INSERT [dbo].[Question] ([ID], [question], [option1], [option2], [option3], [option4], [date], [answer]) VALUES (12, N'A', N'ba', N'12', N'45', N'7', CAST(N'2021-07-20' AS Date), N'1')
GO
INSERT [dbo].[Question] ([ID], [question], [option1], [option2], [option3], [option4], [date], [answer]) VALUES (14, N'ABC', N'A', N'B', N'C', N'D', CAST(N'2021-07-22' AS Date), N'2')
GO
SET IDENTITY_INSERT [dbo].[Question] OFF
GO
USE [master]
GO
ALTER DATABASE [LabWeb_Onlinequiz] SET  READ_WRITE 
GO
