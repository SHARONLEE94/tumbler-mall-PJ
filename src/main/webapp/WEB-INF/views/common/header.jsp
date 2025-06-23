<%--
  Created by IntelliJ IDEA.
  User: sharo
  Date: 2025-06-22
  Time: 오후 5:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>STANLEY - 당신에게 어울리는 색상을 찾아보세요</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        'beige': {
                            50: '#FEFCF8',
                            100: '#F5F1E8',
                            200: '#E8E2D4',
                            300: '#D4C4A8',
                            400: '#C4B08A',
                            500: '#B09C6C',
                            600: '#9A8A5E',
                            700: '#7A6B47',
                            800: '#5A4F35',
                            900: '#3A3323',
                        }
                    }
                }
            }
        }
    </script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/lucide/0.263.1/lucide.min.css" rel="stylesheet">
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body class="bg-beige-50">
<nav class="bg-beige-50 border-b border-beige-200 sticky top-0 z-50">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between items-center h-16">
            <!-- Logo -->
            <a href="/main" class="text-2xl font-bold text-gray-900">
                STANLEY
            </a>

            <!-- Desktop Navigation -->
            <div class="hidden md:flex items-center space-x-8">
                <a href="/main" class="text-gray-900 hover:text-beige-600 transition-colors">홈</a>
                <a href="/board" class="text-gray-900 hover:text-beige-600 transition-colors">게시판</a>
            </div>

            <!-- Desktop Actions -->
            <div class="hidden md:flex items-center space-x-4">
                <a href="/cart" class="text-gray-900 hover:bg-beige-100 p-2 rounded">
                    <i data-lucide="shopping-bag" class="h-5 w-5"></i>
                </a>
                <a href="/login" class="text-gray-900 hover:bg-beige-100 p-2 rounded">
                    <i data-lucide="user" class="h-5 w-5"></i>
                </a>
            </div>

            <!-- Mobile menu button -->
            <div class="md:hidden">
                <button id="mobile-menu-button" class="text-gray-900 p-2">
                    <i data-lucide="menu" class="h-6 w-6"></i>
                </button>
            </div>
        </div>

        <!-- Mobile Navigation -->
        <div id="mobile-menu" class="md:hidden py-4 border-t border-beige-200 hidden">
            <div class="flex flex-col space-y-4">
                <a href="/main" class="text-gray-900 hover:text-beige-600 transition-colors">홈</a>
                <a href="/board" class="text-gray-900 hover:text-beige-600 transition-colors">게시판</a>
                <a href="/cart" class="text-gray-900 hover:text-beige-600 transition-colors">장바구니</a>
                <a href="/login" class="text-gray-900 hover:text-beige-600 transition-colors">로그인</a>
            </div>
        </div>
    </div>
</nav>

<script>
    // Mobile menu toggle
    document.getElementById('mobile-menu-button').addEventListener('click', function() {
        const menu = document.getElementById('mobile-menu');
        menu.classList.toggle('hidden');
    });

    // Initialize Lucide icons
    lucide.createIcons();
</script>

